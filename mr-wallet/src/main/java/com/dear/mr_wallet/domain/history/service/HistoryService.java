package com.dear.mr_wallet.domain.history.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.service.CategoryDbService;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDetailDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDto;
import com.dear.mr_wallet.domain.history.dto.PostHistoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import com.dear.mr_wallet.domain.member.entity.Member;
import com.dear.mr_wallet.domain.member.service.MemberDbService;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.dear.mr_wallet.domain.history.entity.HistoryStatus.DURING_REGULAR_PAYMENT;
import static com.dear.mr_wallet.global.exception.ExceptionCode.CATEGORY_NOT_FOUND;
import static com.dear.mr_wallet.global.exception.ExceptionCode.PAYMENT_CAN_NOT_BE_LATE_THAN_END;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryDbService historyDbService;
    private final CategoryDbService categoryDbService;
    private final MemberDbService memberDbService;

    @Transactional
    public void createHistory(PostHistoryDto post) {
        categoryDbService.findCategoryName(post.getCategoryName(), 1L)
                .orElseThrow(() -> new BusinessLogicException(CATEGORY_NOT_FOUND));
        if (post.getPaymentDate().isAfter(post.getEndDate())) {
            throw new BusinessLogicException(PAYMENT_CAN_NOT_BE_LATE_THAN_END);
        }

        History history = History.builder()
                .title(post.getTitle())
                .amount(post.getAmount())
                .paymentDate(post.getPaymentDate())
                .endDate(post.getEndDate())
                .flexibleAmount(post.getFlexibleAmount())
                .repeatCycle(post.getRepeatCycle())
                .paymentMethod(post.getPaymentMethod())
                .memo(post.getMemo())
                .paymentStatus(false)
                .memberId(1L) // 사용자 식별자 저장 로직 변경 예정
                .build();

        Category findCategory = categoryDbService.findCategoryByName(post.getCategoryName(), 1L);
        Member findMember = memberDbService.ifExistsReturnMember(1L);

        history.setNextPaymentDate(nextPaymentDate(post.getPaymentDate(), post.getRepeatCycle(), post.getEndDate()));
        history.setHistoryStatus(DURING_REGULAR_PAYMENT);
        history.addCategory(findCategory);
        findMember.increaseTotalAmount(post.getAmount());
        findCategory.increaseTotalAmount(post.getAmount());

        historyDbService.saveHistory(history);
    }

    @Transactional
    public void editHistory(PostHistoryDto post, Long historyId) {
        categoryDbService.findCategoryName(post.getCategoryName(), 1L)
                .orElseThrow(() -> new BusinessLogicException(CATEGORY_NOT_FOUND));

        History findHistory = historyDbService.ifExistsReturnHistory(historyId);
        findHistory.editHistory(post);

        if (post.getPaymentDate() != null) {
            findHistory.setNextPaymentDate(
                    nextPaymentDate(post.getPaymentDate(), findHistory.getRepeatCycle(), findHistory.getEndDate()));
        }

        if (post.getCategoryName() != findHistory.getCategory().getName()) {
            Category newCategory =
                    categoryDbService.findCategoryByName(post.getCategoryName(), findHistory.getMemberId());
            findHistory.addCategory(newCategory);
            historyDbService.saveHistory(findHistory);
        }

        Category findCategory = categoryDbService.ifExistsReturnCategory(findHistory.getCategory().getId());
        Member findMember = memberDbService.ifExistsReturnMember(findHistory.getMemberId());

        if (post.getAmount() != findHistory.getAmount()) {
            findCategory.reduceTotalAmount(findHistory.getAmount());
            findCategory.increaseTotalAmount(post.getAmount());

            findMember.reduceTotalAmount(findHistory.getAmount());
            findMember.increaseTotalAmount(post.getAmount());

            findHistory.setAmount(post.getAmount());
        }

        historyDbService.saveHistory(findHistory);
    }

    @Transactional
    public void editPaymentStatus(Long historyId) {
        History findHistory = historyDbService.ifExistsReturnHistory(historyId);
        Category findCategory = categoryDbService.ifExistsReturnCategory(findHistory.getCategory().getId());
        Member findMember = memberDbService.ifExistsReturnMember(findHistory.getMemberId());

        if (findHistory.getPaymentStatus().equals(false)) {
            findHistory.setPaymentStatus(true);
            findCategory.reduceTotalAmount(findHistory.getAmount());
            findMember.reduceTotalAmount(findHistory.getAmount());
        } else {
            findHistory.setPaymentStatus(false);
            findCategory.increaseTotalAmount(findHistory.getAmount());
            findMember.increaseTotalAmount(findHistory.getAmount());
        }

        historyDbService.saveHistory(findHistory);
    }

    public GetHistoryDetailDto getHistoryDetail(Long historyId) {
        return historyDbService.getHistoryDetail(historyId);
    }

    public Page<GetHistoryDto> getHistoryDto(Long categoryId, Pageable pageable) {
        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);
        return historyDbService
                .getHistoryDtoByCategoryIdAndMemberId(categoryId, findCategory.getMemberId(), pageable);
    }

    public void removeHistory(Long historyId) {
        History findHistory = historyDbService.ifExistsReturnHistory(historyId);
        Category findCategory = categoryDbService.ifExistsReturnCategory(findHistory.getCategory().getId());
        Member findMember = memberDbService.ifExistsReturnMember(findHistory.getMemberId());

        findCategory.reduceTotalAmount(findHistory.getAmount());
        findMember.reduceTotalAmount(findHistory.getAmount());

        historyDbService.removeHistory(findHistory);
    }

    public LocalDateTime nextPaymentDate(LocalDateTime paymentDate, String repeatCycle, LocalDateTime endDate) {
        LocalDateTime nextPaymentDate = paymentDate;

        switch (repeatCycle) {
            case "매주":
                nextPaymentDate = nextPaymentDate.plusWeeks(1);
                break;
            case "매달":
                nextPaymentDate = nextPaymentDate.plusMonths(1);
                break;
            case "매년":
                nextPaymentDate = nextPaymentDate.plusYears(1);
                break;
        }

        if (nextPaymentDate.isBefore(endDate) || nextPaymentDate.isEqual(endDate)) return nextPaymentDate;
        else return paymentDate;
    }
}

package com.dear.mr_wallet.domain.history.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.service.CategoryDbService;
import com.dear.mr_wallet.domain.history.dto.PostHistoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import com.dear.mr_wallet.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dear.mr_wallet.domain.history.entity.HistoryStatus.WAITING_FOR_PAYMENT;
import static com.dear.mr_wallet.global.exception.ExceptionCode.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryDbService historyDbService;
    private final CategoryDbService categoryDbService;

    @Transactional
    public void createHistory(PostHistoryDto post) {
        categoryDbService.findCategoryName(post.getCategoryName(), 1L)
                .orElseThrow(() -> new BusinessLogicException(CATEGORY_NOT_FOUND));

        History history = History.builder()
                .title(post.getTitle())
                .amount(post.getAmount())
                .paymentDate(post.getPaymentDate())
                .endDate(post.getEndDate())
                .flexibleAmount(post.getFlexibleAmount())
                .paymentMethod(post.getPaymentMethod())
                .memo(post.getMemo())
                .historyStatus(WAITING_FOR_PAYMENT)
                .paymentStatus(false)
                .memberId(1L) // 사용자 식별자 저장 로직 변경 예정
                .build();

        Category findCategory = categoryDbService.findCategoryByName(post.getCategoryName(), 1L);
        history.addCategory(findCategory);
        findCategory.increaseTotalAmount(post.getAmount());

        historyDbService.saveHistory(history);
        categoryDbService.saveCategory(findCategory);
    }

    @Transactional
    public void editHistory(PostHistoryDto post, Long historyId) {
        History history = historyDbService.ifExistsReturnHistory(historyId);
        history.editHistory(post);

        // 카테고리 변경 로직 추가 예정
        // 총 결제 금액 수정 로직 추가 예정

        historyDbService.saveHistory(history);
    }

    @Transactional
    public void editPaymentStatus(Long historyId) {
        History history = historyDbService.ifExistsReturnHistory(historyId);
        if (history.getPaymentStatus().equals(false)) {
            history.setPaymentStatus(true);
        } else history.setPaymentStatus(false);

        historyDbService.saveHistory(history);
    }

    public void removeHistory(Long historyId) {
        History history = historyDbService.ifExistsReturnHistory(historyId);
        historyDbService.removeHistory(history);
    }
}

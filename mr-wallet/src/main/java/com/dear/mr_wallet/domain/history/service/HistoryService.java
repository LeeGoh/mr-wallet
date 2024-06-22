package com.dear.mr_wallet.domain.history.service;

import com.dear.mr_wallet.domain.history.dto.PostHistoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dear.mr_wallet.domain.history.entity.HistoryStatus.WAITING_FOR_PAYMENT;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryDbService historyDbService;

    @Transactional
    public void createHistory(PostHistoryDto post) {
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
                .build();

        // 사용자 식별자 추가 예정
        // 카테고리 추가 예정(dto의 카테고리명을 카테고리 식별자로 변경하여 저장)
        // 총 결제 금액 수정 로직 추가 예정

        historyDbService.saveHistory(history);
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

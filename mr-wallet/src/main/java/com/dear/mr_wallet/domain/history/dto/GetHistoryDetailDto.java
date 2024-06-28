package com.dear.mr_wallet.domain.history.dto;

import com.dear.mr_wallet.domain.history.entity.HistoryStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetHistoryDetailDto {
    private Long categoryId;
    private String title;
    private Integer amount;
    private LocalDateTime paymentDate;
    private LocalDateTime endDate;
    private LocalDateTime nextPaymentDate;
    private Boolean paymentStatus;
    private Boolean flexibleAmount;
    private String repeatCycle;
    private String paymentMethod;
    private String memo;
    private String historyStatus;

    @Builder
    @QueryProjection
    public GetHistoryDetailDto(Long id, String title, Integer amount, LocalDateTime paymentDate,
                               LocalDateTime endDate, LocalDateTime nextPaymentDate, Boolean paymentStatus,
                               Boolean flexibleAmount, String repeatCycle, String paymentMethod, String memo,
                               HistoryStatus historyStatus) {
        this.categoryId = id;
        this.title = title;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.endDate = endDate;
        this.nextPaymentDate = nextPaymentDate;
        this.paymentStatus = paymentStatus;
        this.flexibleAmount = flexibleAmount;
        this.repeatCycle = repeatCycle;
        this.paymentMethod = paymentMethod;
        this.memo = memo;
        this.historyStatus = historyStatus.getStatus();
    }
}

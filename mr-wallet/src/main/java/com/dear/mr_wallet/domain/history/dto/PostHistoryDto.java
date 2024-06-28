package com.dear.mr_wallet.domain.history.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostHistoryDto {
    private String title;
    private Integer amount;
    private LocalDateTime paymentDate;
    private LocalDateTime endDate;
    private Boolean flexibleAmount;
    private String repeatCycle;
    private String paymentMethod;
    private String memo;
    private String categoryName;

    @Builder
    public PostHistoryDto(String title, Integer amount, LocalDateTime paymentDate,
                          LocalDateTime endDate, Boolean flexibleAmount, String repeatCycle,
                          String paymentMethod, String memo, String categoryName) {
        this.title = title;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.endDate = endDate;
        this.flexibleAmount = flexibleAmount;
        this.repeatCycle = repeatCycle;
        this.paymentMethod = paymentMethod;
        this.memo = memo;
        this.categoryName = categoryName;
    }
}

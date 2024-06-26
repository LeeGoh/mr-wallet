package com.dear.mr_wallet.domain.history.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetHistoryDto {
    private Long id;
    private String title;
    private Integer amount;
    private LocalDateTime paymentDate;
    private Boolean paymentStatus;
    private Boolean flexibleAmount;
    private String paymentMethod;
    private String memo;

    @Builder
    @QueryProjection
    public GetHistoryDto(Long id, String title, Integer amount, LocalDateTime paymentDate,
                         Boolean paymentStatus, Boolean flexibleAmount, String paymentMethod, String memo) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.flexibleAmount = flexibleAmount;
        this.paymentMethod = paymentMethod;
        this.memo = memo;
    }
}

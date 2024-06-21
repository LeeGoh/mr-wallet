package com.dear.mr_wallet.domain.history.entity;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum HistoryStatus {
    WAITING_FOR_PAYMENT(1, "결제 대기"),
    COMPLETE_PAYMENT(2, "결제 완료");

    private Integer stepNumber;
    private String status;

    HistoryStatus(Integer stepNumber, String status) {
        this.stepNumber = stepNumber;
        this.status = status;
    }

    private static final Map<String, HistoryStatus> descriptions =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(HistoryStatus::getStatus, Function.identity())));
}

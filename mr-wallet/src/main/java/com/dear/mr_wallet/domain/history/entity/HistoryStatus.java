package com.dear.mr_wallet.domain.history.entity;

import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dear.mr_wallet.global.exception.ExceptionCode.STATUS_NOT_FOUND;

@Getter
public enum HistoryStatus {
    DURING_REGULAR_PAYMENT("정기결제사용중"),
    END_OF_REGULAR_PAYMENT("정기결제종료");

    private String status;

    HistoryStatus(String status) {
        this.status = status;
    }

    private static final Map<String, HistoryStatus> descriptions =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(HistoryStatus::getStatus, Function.identity())));

    public static HistoryStatus findByStatus(String status) {
        return Optional.ofNullable(descriptions.get(status))
                .orElseThrow(() -> new BusinessLogicException(STATUS_NOT_FOUND));
    }
}

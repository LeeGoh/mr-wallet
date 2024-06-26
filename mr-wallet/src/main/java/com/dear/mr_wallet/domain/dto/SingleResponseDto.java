package com.dear.mr_wallet.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SingleResponseDto<T> {
    private T data;

    @Builder
    public SingleResponseDto(T data) {
        this.data = data;
    }
}

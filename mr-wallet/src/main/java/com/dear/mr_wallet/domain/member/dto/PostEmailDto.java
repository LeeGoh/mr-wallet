package com.dear.mr_wallet.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostEmailDto {
    private String email;

    @Builder
    public PostEmailDto(String email) {
        this.email = email;
    }
}

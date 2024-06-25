package com.dear.mr_wallet.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostNicknameDto {
    private String nickname;

    @Builder
    public PostNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}

package com.dear.mr_wallet.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchNicknameDto {
    private String nickname;

    @Builder
    public PatchNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}

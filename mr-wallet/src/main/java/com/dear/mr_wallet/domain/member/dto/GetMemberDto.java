package com.dear.mr_wallet.domain.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMemberDto {
    private String nickname;
    private String email;
    private Boolean AllowEmail;

    @Builder
    @QueryProjection
    public GetMemberDto(String nickname, String email, Boolean allowEmail) {
        this.nickname = nickname;
        this.email = email;
        AllowEmail = allowEmail;
    }
}

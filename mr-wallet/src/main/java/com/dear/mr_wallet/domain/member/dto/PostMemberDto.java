package com.dear.mr_wallet.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostMemberDto {
    private String nickname;
    private String email;
    private String password;
    private Boolean allowEmail;

    @Builder
    public PostMemberDto(String nickname, String email, String password, Boolean allowEmail) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.allowEmail = allowEmail;
    }
}

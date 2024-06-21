package com.dear.mr_wallet.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private Boolean allowEmail;

    @Setter
    private String roles;

    @Builder
    public Member(Long id, String nickname, String email, String password, Boolean allowEmail, String roles) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password; // 추후 수정
        this.allowEmail = allowEmail;
        this.roles = roles;
    }

    public void editNewPassword(String password) {
        this.password = password;
    }
}

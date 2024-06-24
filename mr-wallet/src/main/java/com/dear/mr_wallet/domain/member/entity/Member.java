package com.dear.mr_wallet.domain.member.entity;

import com.dear.mr_wallet.domain.member.dto.PatchNicknameDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

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
    private Integer totalAmount;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus;

    @Builder
    public Member(Long id, String nickname, String email, String password,
                  Boolean allowEmail, Integer totalAmount, MemberStatus memberStatus) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password; // 추후 수정
        this.allowEmail = allowEmail;
        this.totalAmount = totalAmount;
        this.memberStatus = memberStatus;
    }

    public void editNewPassword(String password) {
        this.password = password;
    }

    public void editNickname(PatchNicknameDto patch) {
        Optional.ofNullable(patch.getNickname())
                .ifPresent(nickname -> this.nickname = patch.getNickname());
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void increaseTotalAmount(Integer amount) {
        this.totalAmount += amount;
    }

    public void reduceTotalAmount(Integer amount) {
        this.totalAmount -= amount;
    }
}

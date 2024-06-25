package com.dear.mr_wallet.domain.member.controller;

import com.dear.mr_wallet.domain.member.dto.PatchNicknameDto;
import com.dear.mr_wallet.domain.member.dto.PostEmailDto;
import com.dear.mr_wallet.domain.member.dto.PostMemberDto;
import com.dear.mr_wallet.domain.member.dto.PostNicknameDto;
import com.dear.mr_wallet.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity createMember(@RequestBody PostMemberDto post) {
        memberService.createMember(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/member/check/email")
    public ResponseEntity checkDuplicateEmail(@RequestBody PostEmailDto post) {
        memberService.checkDuplicateEmail(post.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/member/check/nickname")
    public ResponseEntity checkDuplicateNickname(@RequestBody PostNicknameDto post) {
        memberService.checkDuplicateNickname(post.getNickname());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/member/{member-id}/nickname") // 추후 수정
    public ResponseEntity changeNickname(@RequestBody PatchNicknameDto patch,
                                         @PathVariable("member-id") Long memberId) {
        memberService.changeNickname(patch, memberId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/member/{member-id}") // 추후 수정
    public ResponseEntity changePassword(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/{member-id}") // 추후 수정
    public ResponseEntity getMemberDetail(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/member/{member-id}/delete") // 추후 수정
    public ResponseEntity deleteMember(@PathVariable("member-id") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
}

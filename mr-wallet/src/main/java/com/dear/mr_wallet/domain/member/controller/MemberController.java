package com.dear.mr_wallet.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    @PostMapping("/join")
    public ResponseEntity createMember() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/member/{member-id}") // 추후 수정
    public ResponseEntity editMember(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/member/{member-id}") // 추후 수정
    public ResponseEntity changePassword(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/{member-id}") // 추후 수정
    public ResponseEntity getMemberDetail(@PathVariable("/member/{member-id}") Long memberId) {
        return ResponseEntity.ok().build();
    }
}

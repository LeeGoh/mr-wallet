package com.dear.mr_wallet.domain.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    @PostMapping
    public ResponseEntity createHistory() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity editHistory(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllHistory() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/") // 추후 수정
    public ResponseEntity getMonthHistory() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/category/{category-id}")
    public ResponseEntity getHistoryByCategory(@PathVariable("category-id") Long categoryId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{history-id}")
    public ResponseEntity deleteHistory(@PathVariable("history-id") Long historyId) {
        return ResponseEntity.noContent().build();
    }
}

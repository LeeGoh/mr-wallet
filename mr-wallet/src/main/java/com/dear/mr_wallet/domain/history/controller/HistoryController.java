package com.dear.mr_wallet.domain.history.controller;

import com.dear.mr_wallet.domain.history.dto.PostHistoryDto;
import com.dear.mr_wallet.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping
    public ResponseEntity createHistory(@RequestBody PostHistoryDto post) {
        historyService.createHistory(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{history-id}")
    public ResponseEntity editHistory(@RequestBody PostHistoryDto post,
                                      @PathVariable("history-id") Long historyId) {
        historyService.editHistory(post, historyId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{history-id}/check")
    public ResponseEntity editPaymentStatus(@PathVariable("history-id") Long historyId) {
        historyService.editPaymentStatus(historyId);
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

    @DeleteMapping("/{history-id}")
    public ResponseEntity deleteHistory(@PathVariable("history-id") Long historyId) {
        historyService.removeHistory(historyId);
        return ResponseEntity.noContent().build();
    }
}

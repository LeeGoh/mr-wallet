package com.dear.mr_wallet.domain.history.controller;

import com.dear.mr_wallet.domain.category.dto.GetCategoryDto;
import com.dear.mr_wallet.domain.category.dto.WrapCategoryDto;
import com.dear.mr_wallet.domain.category.service.CategoryService;
import com.dear.mr_wallet.domain.dto.SingleResponseDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDetailDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDto;
import com.dear.mr_wallet.domain.history.dto.PostHistoryDto;
import com.dear.mr_wallet.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;
    private final CategoryService categoryService;

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

    @GetMapping("/month") // 추후 수정
    public ResponseEntity getMonthHistory() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{history-id}")
    public ResponseEntity getHistoryDetail(@PathVariable("history-id") Long historyId) {
        GetHistoryDetailDto history = historyService.getHistoryDetail(historyId);
        return ResponseEntity.ok().body(new SingleResponseDto<>(history));
    }

    @GetMapping("/category/{category-id}")
    public ResponseEntity getHistoryByCategory(@PathVariable("category-id") Long categoryId,
                                               Pageable pageable) {
        GetCategoryDto category = categoryService.getCategoryDto(categoryId);
        Page<GetHistoryDto> histories = historyService.getHistoryDto(categoryId, pageable);
        return ResponseEntity.ok().body(new WrapCategoryDto<>(category, histories));
    }

    @DeleteMapping("/{history-id}")
    public ResponseEntity deleteHistory(@PathVariable("history-id") Long historyId) {
        historyService.removeHistory(historyId);
        return ResponseEntity.noContent().build();
    }
}

package com.dear.mr_wallet.domain.category.controller;

import com.dear.mr_wallet.domain.category.dto.GetCategoryDto;
import com.dear.mr_wallet.domain.category.dto.PostCategoryDto;
import com.dear.mr_wallet.domain.category.dto.WrapCategoryDto;
import com.dear.mr_wallet.domain.category.service.CategoryService;
import com.dear.mr_wallet.domain.dto.MultiResponseDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDto;
import com.dear.mr_wallet.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final HistoryService historyService;

    @PostMapping("/category")
    public ResponseEntity createCategory(@RequestBody PostCategoryDto post) {
        categoryService.createCategory(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/category/{category-id}")
    public ResponseEntity editCategory(@RequestBody PostCategoryDto patch,
                                       @PathVariable("category-id") Long categoryId) {
        categoryService.editCategory(patch, categoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{member-id}")
    public ResponseEntity getCategory(@PathVariable("member-id") Long memberId,
                                      Pageable pageable) {
        Page<GetCategoryDto> categories = categoryService.getCategory(memberId, pageable);
        return ResponseEntity.ok().body(new MultiResponseDto<>(categories));
    }

    @DeleteMapping("category/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable("category-id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("category/{category-id}/all")
    public ResponseEntity deleteCategoryAndHistory(@PathVariable("category-id") Long categoryId) {
        categoryService.deleteCategoryAndHistory(categoryId);
        return ResponseEntity.noContent().build();
    }
}

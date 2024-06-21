package com.dear.mr_wallet.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    @PostMapping("/category")
    public ResponseEntity createCategory() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/category/{category-id}")
    public ResponseEntity editCategory(@PathVariable("category-id") Long categoryId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{member-id}")
    public ResponseEntity getCategory(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("category/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable("category-id") Long categoryId) {
        return ResponseEntity.noContent().build();
    }
}

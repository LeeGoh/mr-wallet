package com.dear.mr_wallet.domain.category.repository;

import com.dear.mr_wallet.domain.category.entity.Category;

import java.util.Optional;

public interface CustomCategoryRepository {
    Category findCategoryByName(String name, Long memberId);
    Optional<String> findCategoryName(String name, Long memberId);
}

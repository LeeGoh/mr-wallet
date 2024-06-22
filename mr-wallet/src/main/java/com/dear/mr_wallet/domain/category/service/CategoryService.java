package com.dear.mr_wallet.domain.category.service;

import com.dear.mr_wallet.domain.category.dto.PostCategoryDto;
import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.history.service.HistoryDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDbService categoryDbService;
    private final HistoryDbService historyDbService;

    @Transactional
    public void createCategory(PostCategoryDto post) {
        Category category = Category.builder()
                .name(post.getName())
                .totalAmount(0)
                .build();

        categoryDbService.saveCategory(category);
    }

    @Transactional
    public void editCategory(PostCategoryDto patch, Long categoryId) {
        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);
        findCategory.editCategoryName(patch);

        categoryDbService.saveCategory(findCategory);
    }

    public void deleteCategory(Long categoryId) {
        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);

        // 카테고리에 담겨있던 기록들의 카테고리를 기본으로 변경

        categoryDbService.removeCategory(findCategory);
    }
}

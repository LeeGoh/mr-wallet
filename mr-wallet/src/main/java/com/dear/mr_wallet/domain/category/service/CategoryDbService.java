package com.dear.mr_wallet.domain.category.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.repository.CategoryRepository;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dear.mr_wallet.global.exception.ExceptionCode.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryDbService {
    private final CategoryRepository categoryRepository;

    public Category ifExistsReturnCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessLogicException(CATEGORY_NOT_FOUND));
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void removeCategory(Category category) {
        categoryRepository.delete(category);
    }
}

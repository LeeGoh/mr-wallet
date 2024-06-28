package com.dear.mr_wallet.domain.category.service;

import com.dear.mr_wallet.domain.category.dto.GetCategoryDto;
import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.repository.CategoryRepository;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dear.mr_wallet.global.exception.ExceptionCode.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryDbService {
    private final CategoryRepository categoryRepository;

    public Category ifExistsReturnCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessLogicException(CATEGORY_NOT_FOUND));
    }

    public boolean ifExistsByCategoryName(String name) {
        return categoryRepository.existsByName(name);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void removeCategory(Category category) {
        categoryRepository.delete(category);
    }

    public Category findCategoryByName(String name, Long memberId) {
        return categoryRepository.findCategoryByName(name, memberId);
    }

    public Optional<String> findCategoryName(String name, Long memberId) {
        return categoryRepository.findCategoryName(name, memberId);
    }

    private List<Category> findAllCategoryByMemberId(Long memberId) {
        return categoryRepository.findAllCategoryByMemberId(memberId);
    }

    public void removeAllCategory(Long memberId) {
        findAllCategoryByMemberId(memberId).forEach(c -> removeCategory(c));
    }

    public Page<GetCategoryDto> getCategory(Long memberId, Pageable pageable) {
        return categoryRepository.getCategory(memberId, pageable);
    }
}

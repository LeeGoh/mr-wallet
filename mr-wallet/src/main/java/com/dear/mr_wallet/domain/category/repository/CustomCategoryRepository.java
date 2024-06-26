package com.dear.mr_wallet.domain.category.repository;

import com.dear.mr_wallet.domain.category.dto.GetCategoryDto;
import com.dear.mr_wallet.domain.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomCategoryRepository {
    Category findCategoryByName(String name, Long memberId);
    Optional<String> findCategoryName(String name, Long memberId);
    List<Category> findAllCategoryByMemberId(Long memberId);
    Page<GetCategoryDto> getCategory(Long memberId, Pageable pageable);
}

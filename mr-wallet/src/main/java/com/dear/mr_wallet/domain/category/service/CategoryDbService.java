package com.dear.mr_wallet.domain.category.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDbService {
    private final CategoryRepository categoryRepository;
}

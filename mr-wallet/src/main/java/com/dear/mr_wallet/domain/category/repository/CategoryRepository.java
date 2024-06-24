package com.dear.mr_wallet.domain.category.repository;

import com.dear.mr_wallet.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CustomCategoryRepository {

}

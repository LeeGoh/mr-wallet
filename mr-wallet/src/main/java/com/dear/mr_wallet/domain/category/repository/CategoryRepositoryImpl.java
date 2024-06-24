package com.dear.mr_wallet.domain.category.repository;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.dear.mr_wallet.domain.category.entity.QCategory.category;

@Repository
public class CategoryRepositoryImpl implements CustomCategoryRepository{
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Category findCategoryByName(String name, Long memberId) {
        return queryFactory
                .select(category)
                .from(category)
                .where(category.name.eq(name).and(category.memberId.eq(memberId)))
                .fetchOne();
    }

    @Override
    public Optional<String> findCategoryName(String name, Long memberId) {
        return Optional.ofNullable(queryFactory
                .select(category.name)
                .from(category)
                .where(category.name.eq(name).and(category.memberId.eq(memberId)))
                .fetchOne());
    }
}

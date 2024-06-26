package com.dear.mr_wallet.domain.category.repository;

import com.dear.mr_wallet.domain.category.dto.GetCategoryDto;
import com.dear.mr_wallet.domain.category.dto.QGetCategoryDto;
import com.dear.mr_wallet.domain.category.entity.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<Category> findAllCategoryByMemberId(Long memberId) {
        return queryFactory
                .select(category)
                .from(category)
                .where(category.memberId.eq(memberId))
                .fetch();
    }

    @Override
    public Page<GetCategoryDto> getCategory(Long memberId, Pageable pageable) {
        List<GetCategoryDto> result = queryFactory
                .select(new QGetCategoryDto(
                        category.id,
                        category.name,
                        category.totalAmount
                )).from(category)
                .where(category.memberId.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = result.size();
        return new PageImpl<>(result, pageable, total);
    }
}

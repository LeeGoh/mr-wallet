package com.dear.mr_wallet.domain.history.repository;

import com.dear.mr_wallet.domain.history.entity.History;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dear.mr_wallet.domain.history.entity.QHistory.history;

@Repository
public class HistoryRepositoryImpl implements CustomHistoryRepository{
    private final JPAQueryFactory queryFactory;

    public HistoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<History> findAllHistoryByCategoryId(Long categoryId, Long memberId) {
        return queryFactory
                .select(history)
                .from(history)
                .where(history.category.id.eq(categoryId)
                        .and(history.memberId.eq(memberId)))
                .fetch();

    }
}

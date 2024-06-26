package com.dear.mr_wallet.domain.history.repository;

import com.dear.mr_wallet.domain.history.dto.GetHistoryDetailDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDto;
import com.dear.mr_wallet.domain.history.dto.QGetHistoryDetailDto;
import com.dear.mr_wallet.domain.history.dto.QGetHistoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public List<History> findAllHistoryByMemberId(Long memberId) {
        return queryFactory
                .select(history)
                .from(history)
                .where(history.memberId.eq(memberId))
                .fetch();
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

    @Override
    public Page<GetHistoryDto> getHistoryDtoByCategoryIdAndMemberId(Long categoryId, Long memberId, Pageable pageable) {
        List<GetHistoryDto> result = queryFactory
                .select(new QGetHistoryDto(
                        history.id,
                        history.title,
                        history.amount,
                        history.paymentDate,
                        history.paymentStatus,
                        history.flexibleAmount,
                        history.paymentMethod,
                        history.memo
                )).from(history)
                .where(history.category.id.eq(categoryId)
                        .and(history.memberId.eq(memberId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = result.size();
        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public GetHistoryDetailDto getHistoryDetail(Long historyId) {
        return queryFactory
                .select(new QGetHistoryDetailDto(
                        history.id,
                        history.title,
                        history.amount,
                        history.paymentDate,
                        history.endDate,
                        history.paymentStatus,
                        history.flexibleAmount,
                        history.paymentMethod,
                        history.memo
                )).from(history)
                .where(history.id.eq(historyId))
                .fetchOne();
    }
}

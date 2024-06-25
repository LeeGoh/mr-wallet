package com.dear.mr_wallet.domain.history.repository;

import com.dear.mr_wallet.domain.history.entity.History;

import java.util.List;

public interface CustomHistoryRepository {
    List<History> findAllHistoryByMemberId(Long memberId);
    List<History> findAllHistoryByCategoryId(Long categoryId, Long memberId);
}

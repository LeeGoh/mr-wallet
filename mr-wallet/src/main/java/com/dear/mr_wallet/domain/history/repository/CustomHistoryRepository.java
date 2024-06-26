package com.dear.mr_wallet.domain.history.repository;

import com.dear.mr_wallet.domain.history.dto.GetHistoryDetailDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomHistoryRepository {
    List<History> findAllHistoryByMemberId(Long memberId);
    List<History> findAllHistoryByCategoryId(Long categoryId, Long memberId);
    Page<GetHistoryDto> getHistoryDtoByCategoryIdAndMemberId(Long categoryId, Long memberId, Pageable pageable);
    GetHistoryDetailDto getHistoryDetail(Long historyId);
}

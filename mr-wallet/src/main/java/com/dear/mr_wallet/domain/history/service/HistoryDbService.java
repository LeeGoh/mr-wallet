package com.dear.mr_wallet.domain.history.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDetailDto;
import com.dear.mr_wallet.domain.history.dto.GetHistoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import com.dear.mr_wallet.domain.history.repository.HistoryRepository;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import com.dear.mr_wallet.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryDbService {
    private final HistoryRepository historyRepository;

    public History ifExistsReturnHistory(Long historyId) {
        return historyRepository.findById(historyId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.HISTORY_NOT_FOUND));
    }

    public void saveHistory(History history) {
        historyRepository.save(history);
    }

    public void removeHistory(History history) {
        historyRepository.delete(history);
    }

    private List<History> findAllHistoryByMemberId(Long memberId) {
        return historyRepository.findAllHistoryByMemberId(memberId);
    }

    public void removeAllHistory(Long memberId) {
        findAllHistoryByMemberId(memberId).forEach(h -> removeHistory(h));
    }

    private List<History> findAllHistoryByCategoryId(Long categoryId, Long memberId) {
        return historyRepository.findAllHistoryByCategoryId(categoryId, memberId);
    }

    public void removeAllHistory(Long categoryId, Long memberId) {
        findAllHistoryByCategoryId(categoryId, memberId)
                .forEach(h -> removeHistory(h));
    }

    public void changeCategory(Long categoryId, Long memberId, Category category) {
        List<History> histories = findAllHistoryByCategoryId(categoryId, memberId);
        for (History h : histories) {
            h.addCategory(category);
            saveHistory(h);
        }
    }
    public Page<GetHistoryDto> getHistoryDtoByCategoryIdAndMemberId(Long categoryId, Long memberId, Pageable pageable) {
        return historyRepository.getHistoryDtoByCategoryIdAndMemberId(categoryId, memberId, pageable);
    }

    public GetHistoryDetailDto getHistoryDetail(Long historyId) {
        return historyRepository.getHistoryDetail(historyId);
    }
}

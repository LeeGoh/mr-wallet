package com.dear.mr_wallet.domain.history.service;

import com.dear.mr_wallet.domain.history.entity.History;
import com.dear.mr_wallet.domain.history.repository.HistoryRepository;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import com.dear.mr_wallet.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

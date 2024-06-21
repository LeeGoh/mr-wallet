package com.dear.mr_wallet.domain.history.service;

import com.dear.mr_wallet.domain.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryDbService {
    private final HistoryRepository historyRepository;
}

package com.dear.mr_wallet.domain.history.repository;

import com.dear.mr_wallet.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long>, CustomHistoryRepository {
}

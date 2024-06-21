package com.dear.mr_wallet.domain.history.entity;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long id;
    private Long memberId;
    private String title;
    private Integer amount;
    private Integer totalAmount;
    private LocalDateTime paymentDate;
    private LocalDateTime endDate;
    private Boolean paymentStatus;
    private Boolean flexibleAmount;
    private String paymentMethod;
    private String memo;

    @Enumerated(value = EnumType.STRING)
    private HistoryStatus historyStatus;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public History(Long id, Long memberId, String title, Integer amount, Integer totalAmount,
                   LocalDateTime paymentDate, LocalDateTime endDate, Boolean paymentStatus,
                   Boolean flexibleAmount, String paymentMethod, String memo, HistoryStatus historyStatus) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.endDate = endDate;
        this.paymentStatus = paymentStatus;
        this.flexibleAmount = flexibleAmount;
        this.paymentMethod = paymentMethod;
        this.memo = memo;
        this.historyStatus = historyStatus;
    }

    public void addCategory(Category category) {
        this.category = category;
    }
}

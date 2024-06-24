package com.dear.mr_wallet.domain.history.entity;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.history.dto.PostHistoryDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public History(Long id, Long memberId, String title, Integer amount,
                   LocalDateTime paymentDate, LocalDateTime endDate, Boolean paymentStatus,
                   Boolean flexibleAmount, String paymentMethod, String memo, HistoryStatus historyStatus) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.amount = amount;
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

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setHistoryStatus(HistoryStatus status) {
        this.historyStatus = status;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void editHistory(PostHistoryDto post) {
        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> this.title = post.getTitle());
        Optional.ofNullable(post.getPaymentDate())
                .ifPresent(paymentDate -> this.paymentDate = post.getPaymentDate());
        Optional.ofNullable(post.getEndDate())
                .ifPresent(endDate -> this.endDate = post.getEndDate());
        Optional.ofNullable(post.getFlexibleAmount())
                .ifPresent(flexibleAmount -> this.flexibleAmount = post.getFlexibleAmount());
        Optional.ofNullable(post.getPaymentMethod())
                .ifPresent(paymentMethod -> this.paymentMethod = post.getPaymentMethod());
        Optional.ofNullable(post.getMemo())
                .ifPresent(memo -> this.memo = post.getMemo());
    }
}

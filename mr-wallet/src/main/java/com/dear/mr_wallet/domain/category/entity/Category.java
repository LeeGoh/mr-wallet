package com.dear.mr_wallet.domain.category.entity;

import com.dear.mr_wallet.domain.category.dto.PostCategoryDto;
import com.dear.mr_wallet.domain.history.entity.History;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;
    private Long memberId;
    private String name;
    private Integer totalAmount;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<History> histories = new ArrayList<>();

    @Builder
    public Category(Long id, Long memberId, String name, Integer totalAmount) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.totalAmount = totalAmount;
    }

    public void addHistories(History history) {
        if (history.getCategory() != this) {
            history.addCategory(this);
        }
        this.histories.add(history);
    }

    public void editCategoryName(PostCategoryDto patch) {
        Optional.ofNullable(patch.getName()).ifPresent(name -> this.name = patch.getName());
    }

    public void increaseTotalAmount(Integer amount) {
        this.totalAmount += amount;
    }

    public void reduceTotalAmount(Integer amount) {
        this.totalAmount -= amount;
    }
}

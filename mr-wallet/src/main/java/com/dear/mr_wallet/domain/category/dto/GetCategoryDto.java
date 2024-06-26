package com.dear.mr_wallet.domain.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetCategoryDto {
    private Long categoryId;
    private String categoryName;
    private Integer totalAmount;

    @Builder
    @QueryProjection
    public GetCategoryDto(Long categoryId, String categoryName, Integer totalAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
    }
}

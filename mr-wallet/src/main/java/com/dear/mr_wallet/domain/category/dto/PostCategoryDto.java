package com.dear.mr_wallet.domain.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCategoryDto {
    private String name;

    @Builder
    public PostCategoryDto(String name) {
        this.name = name;
    }
}

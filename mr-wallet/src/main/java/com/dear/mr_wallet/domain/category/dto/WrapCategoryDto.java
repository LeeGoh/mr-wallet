package com.dear.mr_wallet.domain.category.dto;

import com.dear.mr_wallet.domain.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class WrapCategoryDto<T> {
    private GetCategoryDto category;
    private List<T> data;
    private PageInfo pageInfo;

    @Builder
    public WrapCategoryDto(GetCategoryDto category, Page<T> data) {
        this.category = category;
        this.data = data.getContent();
        this.pageInfo = PageInfo.builder()
                .page(data.getNumber() + 1)
                .size(data.getSize())
                .totalElements(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();;
    }
}

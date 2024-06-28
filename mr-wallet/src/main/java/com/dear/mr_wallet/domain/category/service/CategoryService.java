package com.dear.mr_wallet.domain.category.service;

import com.dear.mr_wallet.domain.category.dto.GetCategoryDto;
import com.dear.mr_wallet.domain.category.dto.PostCategoryDto;
import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.history.service.HistoryDbService;
import com.dear.mr_wallet.domain.member.entity.Member;
import com.dear.mr_wallet.domain.member.service.MemberDbService;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dear.mr_wallet.global.exception.ExceptionCode.CATEGORY_ALREADY_EXIST;
import static com.dear.mr_wallet.global.exception.ExceptionCode.DELETE_IMPOSSIBLE_BASIC_CATEGORY;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDbService categoryDbService;
    private final HistoryDbService historyDbService;
    private final MemberDbService memberDbService;

    @Transactional
    public void createCategory(PostCategoryDto post) {
        if (categoryDbService.ifExistsByCategoryName(post.getName())) {
            throw new BusinessLogicException(CATEGORY_ALREADY_EXIST);
        }

        Category category = Category.builder()
                .name(post.getName())
                .totalAmount(0)
                .memberId(1L) // 추후 수정
                .build();

        categoryDbService.saveCategory(category);
    }

    @Transactional
    public void editCategory(PostCategoryDto patch, Long categoryId) {
        if (categoryDbService.ifExistsByCategoryName(patch.getName())) {
            throw new BusinessLogicException(CATEGORY_ALREADY_EXIST);
        }

        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);
        findCategory.editCategoryName(patch);

        categoryDbService.saveCategory(findCategory);
    }

    public Page<GetCategoryDto> getCategory(Long memberId, Pageable pageable) {
        return categoryDbService.getCategory(memberId, pageable);
    }

    public GetCategoryDto getCategoryDto(Long categoryId) {
        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);
        return GetCategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(findCategory.getName())
                .totalAmount(findCategory.getTotalAmount())
                .build();
    }

    public void deleteCategory(Long categoryId) {
        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);
        Long basicCategoryId = memberDbService.findBasicCategoryId(findCategory.getMemberId());

        if (categoryId.equals(basicCategoryId)) {
            throw new BusinessLogicException(DELETE_IMPOSSIBLE_BASIC_CATEGORY);
        }

        Category findBasicCategory = categoryDbService.ifExistsReturnCategory(basicCategoryId);

        historyDbService.changeCategory(categoryId, findCategory.getMemberId(), findBasicCategory);
        findBasicCategory.increaseTotalAmount(findCategory.getTotalAmount());

        categoryDbService.removeCategory(findCategory);
    }

    public void deleteCategoryAndHistory(Long categoryId) {
        Category findCategory = categoryDbService.ifExistsReturnCategory(categoryId);
        Member findMember = memberDbService.ifExistsReturnMember(findCategory.getMemberId());

        if (categoryId.equals(findMember.getBasicCategoryId())) {
            throw new BusinessLogicException(DELETE_IMPOSSIBLE_BASIC_CATEGORY);
        }

        findMember.reduceTotalAmount(findCategory.getTotalAmount());
        historyDbService.removeAllHistory(categoryId, findCategory.getMemberId());
        categoryDbService.removeCategory(findCategory);
    }
}

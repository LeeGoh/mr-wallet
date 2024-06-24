package com.dear.mr_wallet.domain.member.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.service.CategoryDbService;
import com.dear.mr_wallet.domain.history.service.HistoryDbService;
import com.dear.mr_wallet.domain.member.dto.PatchNicknameDto;
import com.dear.mr_wallet.domain.member.dto.PostMemberDto;
import com.dear.mr_wallet.domain.member.entity.Member;
import com.dear.mr_wallet.domain.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDbService memberDbService;
    private final HistoryDbService historyDbService;
    private final CategoryDbService categoryDbService;

    @Transactional
    public void createMember(PostMemberDto post) {
        Member member = Member.builder()
                .nickname(post.getNickname())
                .email(post.getEmail())
                .password(post.getPassword()) // 추후 수정
                .allowEmail(post.getAllowEmail())
                .totalAmount(0)
                .memberStatus(MemberStatus.MEMBER_ACTIVE)
                .build();

        Member newMember = memberDbService.saveAndReturnMember(member);

        Category category = Category.builder()
                .memberId(newMember.getId())
                .name("기본")
                .totalAmount(0)
                .build();

        categoryDbService.saveCategory(category);
    }

    @Transactional
    public void changeNickname(PatchNicknameDto patch, Long memberId) {
        Member findMember = memberDbService.ifExistsReturnMember(memberId);
        findMember.editNickname(patch);

        memberDbService.saveMember(findMember);
    }

    public void deleteMember(Long memberId) {
        Member findMember = memberDbService.ifExistsReturnMember(memberId);
        findMember.setMemberStatus(MemberStatus.MEMBER_EXITED);

        // 카테고리 삭제 로직 구현
        // 기록 삭제 로직 구현

        memberDbService.saveMember(findMember);
    }
}

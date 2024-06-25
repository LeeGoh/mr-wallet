package com.dear.mr_wallet.domain.member.service;

import com.dear.mr_wallet.domain.category.entity.Category;
import com.dear.mr_wallet.domain.category.service.CategoryDbService;
import com.dear.mr_wallet.domain.history.service.HistoryDbService;
import com.dear.mr_wallet.domain.member.dto.GetMemberDto;
import com.dear.mr_wallet.domain.member.dto.PatchNicknameDto;
import com.dear.mr_wallet.domain.member.dto.PostMemberDto;
import com.dear.mr_wallet.domain.member.entity.Member;
import com.dear.mr_wallet.domain.member.entity.MemberStatus;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dear.mr_wallet.global.exception.ExceptionCode.EMAIL_ALREADY_EXIST;
import static com.dear.mr_wallet.global.exception.ExceptionCode.NICKNAME_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDbService memberDbService;
    private final HistoryDbService historyDbService;
    private final CategoryDbService categoryDbService;

    @Transactional
    public void createMember(PostMemberDto post) {
        if (memberDbService.checkExistEmail(post.getEmail())){
            throw new BusinessLogicException(EMAIL_ALREADY_EXIST);
        }
        if (memberDbService.checkExistNickname(post.getNickname())) {
            throw new BusinessLogicException(NICKNAME_ALREADY_EXIST);
        }

        Member member = Member.builder()
                .nickname(post.getNickname())
                .email(post.getEmail())
                .password(post.getPassword()) // 추후 수정
                .allowEmail(post.getAllowEmail())
                .totalAmount(0)
                .memberStatus(MemberStatus.MEMBER_ACTIVE)
                .build();

        memberDbService.saveMember(member);

        Category category = Category.builder()
                .memberId(member.getId())
                .name("기본")
                .totalAmount(0)
                .build();

        categoryDbService.saveCategory(category);

        member.setBasicCategoryId(category.getId());
        memberDbService.saveMember(member);
    }

    public void checkDuplicateEmail(String email) {
        if (memberDbService.checkExistEmail(email)) {
            throw new BusinessLogicException(EMAIL_ALREADY_EXIST);
        }
    }

    public void checkDuplicateNickname(String nickname) {
        if (memberDbService.checkExistNickname(nickname)) {
            throw new BusinessLogicException(NICKNAME_ALREADY_EXIST);
        }
    }

    @Transactional
    public void changeNickname(PatchNicknameDto patch, Long memberId) {
        Member findMember = memberDbService.ifExistsReturnMember(memberId);
        findMember.editNickname(patch);

        memberDbService.saveMember(findMember);
    }

    public GetMemberDto getMemberInfo(Long memberId) {
        return memberDbService.getMemberInfo(memberId);
    }

    public void deleteMember(Long memberId) {
        Member findMember = memberDbService.ifExistsReturnMember(memberId);
        findMember.setMemberStatus(MemberStatus.MEMBER_EXITED);

        categoryDbService.removeAllCategory(memberId);
        historyDbService.removeAllHistory(memberId);

        findMember.reduceTotalAmount(findMember.getTotalAmount());
        memberDbService.saveMember(findMember);
    }
}

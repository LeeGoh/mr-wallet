package com.dear.mr_wallet.domain.member.service;

import com.dear.mr_wallet.domain.member.dto.GetMemberDto;
import com.dear.mr_wallet.domain.member.entity.Member;
import com.dear.mr_wallet.domain.member.repository.MemberRepository;
import com.dear.mr_wallet.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dear.mr_wallet.global.exception.ExceptionCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberDbService {
    private final MemberRepository memberRepository;

    public Member ifExistsReturnMember(Long historyId) {
        return memberRepository.findById(historyId)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public void removeMember(Member member) {
        memberRepository.delete(member);
    }

    public boolean checkExistEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean checkExistNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public Long findBasicCategoryId(Long memberId) {
        return memberRepository.findBasicCategoryId(memberId);
    }

    public GetMemberDto getMemberInfo(Long memberId) {
        return memberRepository.getMemberInfo(memberId);
    }
}

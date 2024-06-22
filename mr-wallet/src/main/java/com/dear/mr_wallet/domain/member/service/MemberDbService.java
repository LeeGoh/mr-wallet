package com.dear.mr_wallet.domain.member.service;

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

    public Member saveAndReturnMember(Member member) {
        return memberRepository.save(member);
    }

    public void removeMember(Member member) {
    }
}

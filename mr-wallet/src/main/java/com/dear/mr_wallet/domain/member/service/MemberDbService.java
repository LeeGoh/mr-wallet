package com.dear.mr_wallet.domain.member.service;

import com.dear.mr_wallet.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDbService {
    private final MemberRepository memberRepository;
}

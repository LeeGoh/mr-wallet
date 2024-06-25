package com.dear.mr_wallet.domain.member.repository;

import com.dear.mr_wallet.domain.member.dto.GetMemberDto;

public interface CustomMemberRepository {
    Long findBasicCategoryId(Long memberId);
    GetMemberDto getMemberInfo(Long memberId);
}

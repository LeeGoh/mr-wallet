package com.dear.mr_wallet.domain.member.repository;

import com.dear.mr_wallet.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {

}

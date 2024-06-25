package com.dear.mr_wallet.domain.member.repository;

import com.dear.mr_wallet.domain.member.dto.GetMemberDto;
import com.dear.mr_wallet.domain.member.dto.QGetMemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.dear.mr_wallet.domain.member.entity.QMember.member;

@Repository
public class MemberRepositoryImpl implements CustomMemberRepository{
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Long findBasicCategoryId(Long memberId) {
        return queryFactory
                .select(member.basicCategoryId)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();

    }

    @Override
    public GetMemberDto getMemberInfo(Long memberId) {
        return queryFactory
                .select(new QGetMemberDto(
                        member.nickname,
                        member.email,
                        member.allowEmail
                )).from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}

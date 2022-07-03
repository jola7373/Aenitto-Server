package com.firefighter.aenitto.members.repository;

import com.firefighter.aenitto.members.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@Qualifier(value = "memberRepositoryImpl")
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final EntityManager em;

    @Override
    public Member findByMemberId(UUID memberId) {
        return em.find(Member.class, memberId);
    }
}

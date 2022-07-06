package com.firefighter.aenitto.members.repository;

import com.firefighter.aenitto.members.domain.Member;

import java.util.UUID;

public interface MemberRepository {
    public Member findByMemberId(UUID memberId);

    public Member updateMember(Member member);

    public void saveMember(Member member);
}

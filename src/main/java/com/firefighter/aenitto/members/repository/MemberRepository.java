package com.firefighter.aenitto.members.repository;

import com.firefighter.aenitto.members.domain.Member;

import java.util.UUID;

public interface MemberRepository {
    public Member findByMemberId(UUID memberId);

    public Member updateMember(Member member);

    public Member saveMember(Member member);

    public Member findBySocialId(String socialId);
}

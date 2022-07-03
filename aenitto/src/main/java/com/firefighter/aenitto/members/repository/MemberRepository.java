package com.firefighter.aenitto.members.repository;

import com.firefighter.aenitto.members.domain.Member;

public interface MemberRepository {
    public Member findByMemberId(Long memberId);
}

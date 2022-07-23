package com.firefighter.aenitto.members.repository;

import com.firefighter.aenitto.members.domain.Member;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    EntityManager em;

    @Qualifier("memberRepositoryImpl")
    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setExampleMember() {
        member = memberBuilder("Leo", "socialId입니다");
    }

    @DisplayName("Member 저장 테스트")
    @Test
    void memberSaveTest() {
        // given
        memberRepository.saveMember(member);
        em.flush();
        em.clear();

        // when
        Member findMember = em.find(Member.class, member.getId());

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getNickname()).isEqualTo(member.getNickname());

    }

    @DisplayName("Member 값 수정 테스트")
    @Test
    void memberMergeTest() {
        // given
        em.persist(member);
        em.flush();
        em.clear();

        // when (member는 detached 상태)
        // detached 상태를 persist 할 경우 Exception
        assertThatExceptionOfType(InvalidDataAccessApiUsageException.class)
                .isThrownBy(() -> {
                    memberRepository.saveMember(member);
                });


        member.changeNickname("LeoLeo");
        Member mergedMember = memberRepository.updateMember(member);

        em.flush();
        em.clear();

        assertThat(mergedMember.getId()).isEqualTo(member.getId());

        Member findMember = em.find(Member.class, member.getId());

        // then
        assertThat(findMember).isNotNull();
        assertThat(findMember.getId()).isEqualTo(mergedMember.getId());
        assertThat(findMember.getNickname()).isEqualTo("LeoLeo");
    }

    @DisplayName("Member 소셜 id로 찾기")
    @Test
    void 사용자존재테스트(){
        //when
        final Member result = memberRepository.saveMember(member);
        final Member findResult = memberRepository.findBySocialId(member.getSocialId());

        //then
        assertThat(findResult.getId()).isNotNull();
        assertThat(findResult.getId()).isEqualTo(member.getId());
        assertThat(findResult.getSocialId()).isEqualTo("socialId입니다");
    }

    private Member memberBuilder(String nickname, String socialId) {
        return Member.builder()
                .nickname(nickname)
                .socialId(socialId)
                .build();
    }

}
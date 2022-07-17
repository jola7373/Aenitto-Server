package com.firefighter.aenitto.auth.service;

import com.firefighter.aenitto.auth.domain.RefreshToken;
import com.firefighter.aenitto.auth.repository.RefreshTokenRepository;
import com.firefighter.aenitto.common.exception.auth.RefreshTokenExistException;
import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.members.repository.MemberRepository;
import com.firefighter.aenitto.members.repository.MemberRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    private final UUID memberId = UUID.fromString("b48617b2-090d-4ee6-9033-b99f99d98304");

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private AuthServiceImpl target;

    private RefreshToken refreshToken;

    @BeforeEach
    void setup() {
        this.refreshToken = RefreshToken.builder()
                .memberId(UUID.fromString("b48617b2-090d-4ee6-9033-b99f99d98304"))
                .refreshToken("refreshToken입니다123123")
                .build();
    }

//    @Test
//    @DisplayName("임시 login 성공 - 회원가입 되지 않은 유저")
//    public void temp_login_success_notExistUser() {
//        // given
//        doReturn(null).when(memberRepository).findBySocialId(socialId);
//        doReturn(Member.builder().build()).when(memberRepository).saveMember(any(Member.class));
//        doReturn(RefreshToken.builder().build()).when(refreshTokenRepository).saveRefreshToken(any(RefreshToken.class));
//
//        // when
//        final Member resultMember = target.saveMember(socialId);
//        final RefreshToken resultRefreshToken = target.saveRefreshToken(memberId);
//
//        //then
//        assertThat(resultRefreshToken.getMemberId()).isNotNull();
//        assertThat(resultMember.getId()).isNotNull();
//
//        // then
//       assertAll(
//               () -> verify(memberRepository).saveMember(any(Member.class)),
//               () -> verify(refreshTokenRepository).saveRefreshToken(any(RefreshToken.class))
//       );
//    }
}

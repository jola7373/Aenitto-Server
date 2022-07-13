package com.firefighter.aenitto.auth.service;

import com.firefighter.aenitto.auth.domain.RefreshToken;
import com.firefighter.aenitto.auth.repository.RefreshTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceTest {

    private final UUID memberId = UUID.fromString("b48617b2-090d-4ee6-9033-b99f99d98304");

    @InjectMocks
    private RefreshTokenService target;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

//    @Test
//    public void 새로운refreshToken등록실패_이미존재함() {
//        // given
//        doReturn(RefreshToken.builder().build()).when(refreshTokenRepository).findByMemberId(memberId);
//
//        // when
//        final RefreshTokenException result = assertThrows(RefreshTokenException.class, () -> target.addMembership(userId, membershipType, point));
//
//        // then
//        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
//    }
}

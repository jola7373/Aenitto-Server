package com.firefighter.aenitto.auth.service;

import com.firefighter.aenitto.auth.domain.RefreshToken;
import com.firefighter.aenitto.auth.repository.RefreshTokenRepository;
import com.firefighter.aenitto.common.exception.auth.AuthErrorCode;
import com.firefighter.aenitto.common.exception.auth.RefreshTokenExistException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.firefighter.aenitto.members.MemberFixture.MEMBER_1;
import static com.firefighter.aenitto.rooms.RoomFixture.MEMBER_ROOM_1;
import static com.firefighter.aenitto.rooms.RoomFixture.ROOM_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceTest {

    private final UUID memberId = UUID.fromString("b48617b2-090d-4ee6-9033-b99f99d98304");

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private RefreshTokenService target = new RefreshTokenServiceImpl(this.refreshTokenRepository);
//    private RefreshTokenServiceImpl target;

    private RefreshToken refreshToken;

    @BeforeEach
    void setup() {
        this.refreshToken = RefreshToken.builder()
                .memberId(UUID.fromString("b48617b2-090d-4ee6-9033-b99f99d98304"))
                .refreshToken("refreshToken입니다123123")
                .build();
    }

    @Test
    @DisplayName("refresh토큰 저장 실패")
    public void 새로운refreshToken등록실패_이미존재함() {
        // given
        doReturn(RefreshToken.builder().build()).when(refreshTokenRepository).findByMemberId(memberId);

        // when
        final RefreshTokenExistException result = assertThrows(RefreshTokenExistException.class, () -> target.saveRefreshToken(memberId));

        // then
        assertThat(result.getErrorCode()).isEqualTo(AuthErrorCode.DUPLICATED_AUTH_REGISTER);
    }
}

package com.firefighter.aenitto.auth.repository;

import com.firefighter.aenitto.auth.domain.RefreshToken;
import com.firefighter.aenitto.rooms.domain.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository {
    RefreshToken saveRefreshToken(RefreshToken refreshToken);

}

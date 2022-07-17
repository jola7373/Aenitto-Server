package com.firefighter.aenitto.auth.service;

import com.firefighter.aenitto.auth.domain.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AuthService {
    RefreshToken saveRefreshToken(UUID memberId);
}

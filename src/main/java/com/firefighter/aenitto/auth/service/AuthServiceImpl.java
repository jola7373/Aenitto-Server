package com.firefighter.aenitto.auth.service;


import com.firefighter.aenitto.auth.domain.RefreshToken;
import com.firefighter.aenitto.auth.repository.RefreshTokenRepository;
import com.firefighter.aenitto.common.exception.auth.RefreshTokenExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public RefreshToken saveRefreshToken(UUID memberId){
        final RefreshToken result = refreshTokenRepository.findByMemberId(memberId);
        if(result != null){
            throw new RefreshTokenExistException();
        }else{
            System.out.println("nulling");
        }
        return null;
    }
}

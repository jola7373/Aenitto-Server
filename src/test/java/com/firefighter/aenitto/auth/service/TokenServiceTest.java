package com.firefighter.aenitto.auth.service;


import com.firefighter.aenitto.auth.token.Token;
import com.firefighter.aenitto.rooms.service.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    private UUID testUUID = UUID.randomUUID();
    @InjectMocks
    @Autowired
    private TokenService tokenService;

    @Test
    public void 토큰_생성하기() {
        Token token = tokenService.generateToken("UUID입니다","가나다라");
        System.out.println(">>>>>>>>>>>>>> token = " + token);
    }

    @Test
    public void access_토큰_검증하기() {
        String token = tokenService.generateAccessToken("UUID입니다","가나다라");
        System.out.println(token);
        tokenService.verifyToken(token);
    }


}

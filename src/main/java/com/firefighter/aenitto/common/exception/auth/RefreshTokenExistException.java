package com.firefighter.aenitto.common.exception.auth;

import com.firefighter.aenitto.common.exception.CustomException;

public class RefreshTokenExistException extends CustomException {
    private static final String MESSAGE = "이미 Refresh Token이 저장되어 있습니다.";
    private static final AuthErrorCode code = AuthErrorCode.DUPLICATED_AUTH_REGISTER;

    public RefreshTokenExistException(){
        super(code);
    }
}


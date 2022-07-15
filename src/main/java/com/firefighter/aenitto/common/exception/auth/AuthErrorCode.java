package com.firefighter.aenitto.common.exception.auth;

import com.firefighter.aenitto.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    DUPLICATED_AUTH_REGISTER(HttpStatus.BAD_REQUEST, "Duplicated Auth Register Request"),
    ;

    private final HttpStatus status;
    private final String message;
}

package com.firefighter.aenitto.common.exception.test;

import com.firefighter.aenitto.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements ErrorCode {
    TEST_ERROR_CODE(BAD_REQUEST, "테스트 에러 메시지 입니다")
    ;
    private final HttpStatus status;
    private final String message;
}

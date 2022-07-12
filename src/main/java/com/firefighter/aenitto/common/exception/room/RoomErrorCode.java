package com.firefighter.aenitto.common.exception.room;

import com.firefighter.aenitto.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RoomErrorCode implements ErrorCode {
    ;
    private final HttpStatus status;
    private final String message;
}

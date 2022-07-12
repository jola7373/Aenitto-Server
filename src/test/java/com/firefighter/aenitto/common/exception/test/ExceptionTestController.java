package com.firefighter.aenitto.common.exception.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/test")
public class ExceptionTestController {
    @GetMapping("/custom-exception")
    public ResponseEntity custom() {
        throw new TestException();
    }

    @PostMapping("/bind-exception")
    public ResponseEntity bind(
            @Valid @RequestBody RequestDto requestDto
    ) {
        return null;
    }


    @Getter
    @NoArgsConstructor
    public static class RequestDto {
        @NotNull
        private String name;
        @NotNull
        @Min(0) @Max(100)
        private int age;

        @Builder
        public RequestDto(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
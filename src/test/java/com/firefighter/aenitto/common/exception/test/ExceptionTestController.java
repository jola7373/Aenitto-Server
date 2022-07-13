package com.firefighter.aenitto.common.exception.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
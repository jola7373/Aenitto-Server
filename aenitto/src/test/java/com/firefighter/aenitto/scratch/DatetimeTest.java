package com.firefighter.aenitto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class scratch {

    @DisplayName("String -> LocalDate 테스트")
    @Test
    void stringToLocalDateTest() {
        // given
        String dateString = "2022.06.30";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.mm.dd");

        // when
        LocalDate parse = LocalDate.parse(dateString, dateTimeFormatter);

        // then

    }

}

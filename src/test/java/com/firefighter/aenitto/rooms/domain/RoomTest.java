package com.firefighter.aenitto.rooms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

public class RoomTest {
    @DisplayName("6개의 난수 (0-9 혹은 A-Z의 upper-case) 생성")
    @Test
    public void createRandomSixNumString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        Room room = Room.builder().build();
        Method randomSixNumUpperString = Room.class.getDeclaredMethod("randomSixNumUpperString");
        randomSixNumUpperString.setAccessible(true);

        // when
        String random = (String) randomSixNumUpperString.invoke(room);

        // then
        assertThat(random.length()).isEqualTo(6);
        assertThat(random).isUpperCase();
    }
}


package com.firefighter.aenitto.rooms;

import com.firefighter.aenitto.rooms.domain.MemberRoom;
import com.firefighter.aenitto.rooms.domain.Room;

import java.time.LocalDate;

public class RoomFixture {
    public static final Room ROOM_1 = Room.builder()
            .id(1L)
            .title("방제목")
            .capacity(10)
            .startDate(LocalDate.of(2022, 6, 20))
            .endDate(LocalDate.of(2022, 6, 30))
            .build();

    public static final MemberRoom MEMBER_ROOM_1 = MemberRoom.builder()
            .id(1L)
            .admin(false)
            .colorIdx(1)
            .build();

}

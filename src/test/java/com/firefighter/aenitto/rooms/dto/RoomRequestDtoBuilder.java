package com.firefighter.aenitto.rooms.dto;

import com.firefighter.aenitto.rooms.dto.request.CreateRoomRequest;

public class RoomRequestDtoBuilder {

    public static CreateRoomRequest createRoomRequest() {
        return CreateRoomRequest.builder()
                .title("title")
                .capacity(10)
                .startDate("2022.06.20")
                .endDate("2022.06.30")
                .colorIdx(2)
                .build();
    }
}

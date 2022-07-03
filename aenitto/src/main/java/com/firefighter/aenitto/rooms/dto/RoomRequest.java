package com.firefighter.aenitto.rooms.dto;

import com.firefighter.aenitto.common.utils.DateConverter;
import com.firefighter.aenitto.rooms.domain.Room;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomRequest {
    private final String title;
    private final int capacity;
    private final String startDate;
    private final String endDate;

    public Room toEntity() {
        return Room.builder()
                .title(title)
                .capacity(capacity)
                .startDate(DateConverter.stringToLocalDate(startDate))
                .endDate(DateConverter.stringToLocalDate(endDate))
                .build();
    }

    @Builder
    public RoomRequest(String title, int capacity, String startDate, String endDate) {
        this.title = title;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

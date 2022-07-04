package com.firefighter.aenitto.rooms.dto;

import com.firefighter.aenitto.common.utils.DateConverter;
import com.firefighter.aenitto.rooms.domain.Room;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(force = true)
public class RoomRequest {
    @NotNull
    @Size(min = 1, max = 8)
    private final String title;
    @NotNull
    @Min(5) @Max(15)
    private final int capacity;
    @NotNull
    private final String startDate;
    @NotNull
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

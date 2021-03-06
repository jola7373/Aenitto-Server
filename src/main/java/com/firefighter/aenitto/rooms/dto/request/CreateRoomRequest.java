package com.firefighter.aenitto.rooms.dto.request;

import com.firefighter.aenitto.common.utils.DateConverter;
import com.firefighter.aenitto.rooms.domain.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(force = true)
public class CreateRoomRequest {

    @Valid
    private final RoomRequest room;
    @Valid
    private final MemberRequest member;

    public Room toEntity() {
        return Room.builder()
                .title(room.title)
                .capacity(room.capacity)
                .startDate(DateConverter.stringToLocalDate(room.startDate))
                .endDate(DateConverter.stringToLocalDate(room.endDate))
                .build();
    }

    @Builder
    public CreateRoomRequest(String title, int capacity, String startDate, String endDate, int colorIdx) {
        this.room = RoomRequest.builder()
                .capacity(capacity)
                .startDate(startDate)
                .endDate(endDate)
                .title(title)
                .build();
        this.member = new MemberRequest(colorIdx);
    }


    @Getter
    @NoArgsConstructor(force = true)
    public static class RoomRequest {
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

        @Builder
        private RoomRequest(String title, int capacity, String startDate, String endDate) {
            this.title = title;
            this.capacity = capacity;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    public static class MemberRequest {
        private final int colorIdx;
    }
}
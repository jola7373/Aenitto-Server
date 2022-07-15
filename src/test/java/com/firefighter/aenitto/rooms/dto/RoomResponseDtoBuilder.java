package com.firefighter.aenitto.rooms.dto;

import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.response.VerifyInvitationResponse;


public class RoomResponseDtoBuilder {

    public static VerifyInvitationResponse verifyInvitationResponse(Room room) {
        return VerifyInvitationResponse.from(room);
    }
}

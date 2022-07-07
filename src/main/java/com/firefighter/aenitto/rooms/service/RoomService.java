package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.rooms.dto.request.CreateRoomRequest;

public interface RoomService {
    public Long createRoom(Member member, CreateRoomRequest createRoomRequest);
}

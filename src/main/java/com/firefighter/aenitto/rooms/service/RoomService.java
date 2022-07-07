package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.rooms.dto.CreateRoomRequest;

public interface RoomService {
    public Long createRoom(Member member, CreateRoomRequest createRoomRequest);
}

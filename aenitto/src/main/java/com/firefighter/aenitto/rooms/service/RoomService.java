package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.RoomRequest;

public interface RoomService {
    public Room createRoom(Long memberId, RoomRequest roomRequest);
}

package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.RoomRequest;

import java.util.UUID;

public interface RoomService {
    public Room createRoom(UUID memberId, RoomRequest roomRequest);
}

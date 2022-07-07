package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.RoomRequest;

import java.util.UUID;

public interface RoomService {
    public Long createRoom(Member member, RoomRequest roomRequest);
}

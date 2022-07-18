package com.firefighter.aenitto.rooms.repository;


import com.firefighter.aenitto.rooms.domain.MemberRoom;
import com.firefighter.aenitto.rooms.domain.Room;

import java.util.UUID;

public interface RoomRepository {
    public Room saveRoom(Room room);
    public Room mergeRoom(Room room);
    public Room findRoomById(Long id);
    public Room findByInvitation(String invitation);
    public MemberRoom findMemberRoomById(UUID memberId, Long roomId);
}

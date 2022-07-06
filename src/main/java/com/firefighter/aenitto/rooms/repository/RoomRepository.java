package com.firefighter.aenitto.rooms.repository;


import com.firefighter.aenitto.rooms.domain.Room;

public interface RoomRepository {
    public void saveRoom(Room room);

    public Room mergeRoom(Room room);
    public Room findRoomById(Long id);

    public Room findByInvitation(String invitation);
}

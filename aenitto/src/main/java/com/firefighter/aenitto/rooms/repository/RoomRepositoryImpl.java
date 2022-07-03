package com.firefighter.aenitto.rooms.repository;

import com.firefighter.aenitto.rooms.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Qualifier(value = "roomRepositoryImpl")
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {
    private final EntityManager em;

    @Override
    public void saveRoom(Room room) {
        em.persist(room);
    }

    @Override
    public Room findRoomById(Long id) {
        return em.find(Room.class, id);
    }
}

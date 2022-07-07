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
    public Room saveRoom(Room room) {
        em.persist(room);
        return room;
    }

    @Override
    public Room mergeRoom(Room room) {
        return em.merge(room);
    }

    @Override
    public Room findRoomById(Long id) {
        return em.find(Room.class, id);
    }

    @Override
    public Room findByInvitation(String invitation) {
        return em.createQuery(
                        "SELECT r" +
                                " FROM Room r" +
                                " WHERE r.invitation = :invitation", Room.class)
                .setParameter("invitation", invitation)
                .getSingleResult();
    }
}

package com.firefighter.aenitto.rooms.repository;

import com.firefighter.aenitto.rooms.domain.MemberRoom;
import com.firefighter.aenitto.rooms.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

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

    @Override
    public MemberRoom findMemberRoomById(UUID memberId, Long roomId) {
        return em.createQuery(
                        "SELECT mr" +
                                " FROM MemberRoom mr" +
                                " JOIN mr.room r" +
                                " JOIN mr.member m" +
                                " WHERE m.id = :memberId" +
                                " AND r.id = :roomId", MemberRoom.class)
                .setParameter("memberId", memberId)
                .setParameter("roomId", roomId)
                .getSingleResult();
    }
}

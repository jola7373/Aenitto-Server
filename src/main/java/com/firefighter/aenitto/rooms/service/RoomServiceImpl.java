package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.members.repository.MemberRepository;
import com.firefighter.aenitto.rooms.domain.MemberRoom;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.request.CreateRoomRequest;
import com.firefighter.aenitto.rooms.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value = "roomServiceImpl")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Qualifier("roomRepositoryImpl")
    private final RoomRepository roomRepository;

    @Qualifier("memberRepositoryImpl")
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public Long createRoom(Member member, CreateRoomRequest createRoomRequest) {
        // Dto -> Entity
        final Room room = createRoomRequest.toEntity();

        // Room invitation 생성 -> 존재하지 않는 random 코드 나올 때 까지.
        do {
            room.createInvitation();
            try {
                roomRepository.findByInvitation(room.getInvitation());
            } catch (EmptyResultDataAccessException e) {
                break;
            }
        } while (true);

        // admin MemberRoom 생성 및 persist
        MemberRoom memberRoom = MemberRoom.builder()
                .admin(true)
                .build();

        memberRoom.setMemberRoom(member, room);

        memberRepository.updateMember(member);
        return roomRepository.saveRoom(room).getId();
    }

}

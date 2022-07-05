package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.members.repository.MemberRepository;
import com.firefighter.aenitto.members.repository.MemberRepositoryImpl;
import com.firefighter.aenitto.rooms.domain.MemberRoom;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.RoomRequest;
import com.firefighter.aenitto.rooms.repository.RoomRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @InjectMocks
    private RoomServiceImpl target;

    @Mock
    private RoomRepositoryImpl roomRepository;

    @Mock
    private MemberRepositoryImpl memberRepository;

    @DisplayName("방 생성 성공")
    @Test
    void createRoomTest() {
        // mock
        when(roomRepository.findByInvitation(anyString()))
                .thenThrow(EmptyResultDataAccessException.class)
                .thenThrow(EmptyResultDataAccessException.class)
                .thenThrow(EmptyResultDataAccessException.class)
                .thenReturn(Room.builder().build());
        when(memberRepository.findByMemberId(any(UUID.class))).thenReturn(Member.builder().nickname("리오").build());


        // given
        RoomRequest roomRequest = RoomRequest.builder()
                .title("방제목")
                .capacity(10)
                .startDate("2022.06.22")
                .endDate("2022.07.23")
                .build();


        // when
        Room room = target.createRoom(UUID.randomUUID(), roomRequest);

        // then
        assertThat(room.getMemberRooms()).hasSize(1);
        MemberRoom memberRoom = room.getMemberRooms().get(0);
        assertThat(memberRoom.isAdmin()).isTrue();


    }


}

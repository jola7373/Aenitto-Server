package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.members.repository.MemberRepositoryImpl;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.CreateRoomRequest;
import com.firefighter.aenitto.rooms.repository.RoomRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static com.firefighter.aenitto.rooms.RoomFixture.ROOM_1;
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

    // Fixtures
    private Room room;

    @BeforeEach
    void setup() {
        room = ROOM_1;
    }

    @DisplayName("방 생성 성공")
    @Test
    void createRoomTest() {
        // mock
        when(roomRepository.findByInvitation(anyString()))
                .thenThrow(EmptyResultDataAccessException.class)
                .thenThrow(EmptyResultDataAccessException.class)
                .thenThrow(EmptyResultDataAccessException.class)
                .thenReturn(Room.builder().build());
        when(roomRepository.saveRoom(any(Room.class)))
                .thenReturn(room);

        // given
        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .title("방제목")
                .capacity(10)
                .startDate("2022.06.22")
                .endDate("2022.07.23")
                .build();

        // when
        Long roomId = target.createRoom(Member.builder().build(), createRoomRequest);

        // then
        assertThat(roomId).isEqualTo(1L);
        verify(roomRepository, times(1)).saveRoom(any(Room.class));
        verify(memberRepository, times(1)).updateMember(any(Member.class));
    }

}

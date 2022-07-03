package com.firefighter.aenitto.rooms.repository;

import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.domain.RoomState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RoomRepositoryImplTest {
    @Autowired EntityManager em;
    @Autowired RoomRepositoryImpl roomRepository;

    Room room;

    @BeforeEach
    void setRoom() {
        this.room = Room.builder()
                .title("방제목")
                .capacity(10)
                .invitation("123456")
                .startDate(LocalDate.of(2022, 6, 27))
                .endDate(LocalDate.of(2022, 6, 30))
                .build();
    }


    @DisplayName("Room Entity 테스트")
    @Test
    void roomEntityTest() {
        // given
        em.persist(room);
        em.flush();

        // when
        Room findRoom = em.find(Room.class, room.getId());

        // then
        assertThat(findRoom.getCapacity()).isEqualTo(10);
        assertThat(findRoom.getState()).isEqualTo(RoomState.PRE);
        assertThat(findRoom.isDeleted()).isEqualTo(false);
        assertThat(findRoom.getStartDate()).isEqualTo(LocalDate.of(2022, 6, 27));
    }


    @DisplayName("RoomRepository Room 저장 테스트")
    @Test
    void persistRoom() {
        // given
        roomRepository.saveRoom(room);
        em.flush();

        // when
        Room roomById = roomRepository.findRoomById(room.getId());

        // then
        assertThat(room).isEqualTo(roomById);
    }

    @DisplayName("초대코드로 방 검색 -> 성공")
    @Test
    void findByInvitationTest() {
        // given
        roomRepository.saveRoom(room);
        em.flush();

        // when
        Room byInvitation = roomRepository.findByInvitation("123456");

        // then
        assertThat(byInvitation).isEqualTo(room);
        assertThat(byInvitation.getTitle()).isEqualTo("방제목");
    }

    @DisplayName("초대코드로 방 검색 -> 실패 (초대코드 없음)")
    @Test
    void findByInvitationTestFailure() {
        // given
        roomRepository.saveRoom(room);
        em.flush();

        // exception throw
        assertThatThrownBy(() -> {
            Room byInvitation = roomRepository.findByInvitation("123466");
        })
                .isInstanceOf(EmptyResultDataAccessException.class);

    }

}
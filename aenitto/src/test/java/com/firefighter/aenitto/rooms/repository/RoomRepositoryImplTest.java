package com.firefighter.aenitto.rooms.repository;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.domain.RoomState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
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

    @DisplayName("Room Entity 테스트")
    @Test
    void roomEntityTest() {
        // given
        Room room = Room.builder()
                .capacity(10)
                .invitation("123456")
                .startDate("2022.06.27")
                .endDate("2022.06.30")
                .build();

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
        Room room = Room.builder()
                .invitation("123456")
                .startDate("2022.06.27")
                .endDate("2022.06.30")
                .capacity(10)
                .build();

        roomRepository.saveRoom(room);
        em.flush();

        // when
        Room roomById = roomRepository.findRoomById(room.getId());

        // then
        assertThat(room).isEqualTo(roomById);
    }

}
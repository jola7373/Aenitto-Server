package com.firefighter.aenitto.rooms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.request.CreateRoomRequest;
import com.firefighter.aenitto.rooms.dto.request.VerifyInvitationRequest;
import com.firefighter.aenitto.rooms.dto.response.VerifyInvitationResponse;
import com.firefighter.aenitto.rooms.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.firefighter.aenitto.rooms.RoomFixture.ROOM_1;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class RoomControllerTest {
    @InjectMocks
    RoomController roomController;

    @Mock @Qualifier("roomServiceImpl")
    RoomService roomService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    // Fixture
    private Room room;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
        objectMapper = new ObjectMapper();
        room = ROOM_1;
    }

    @DisplayName("방 생성 -> 성공")
    @Test
    void createRoom() throws Exception {
        // Mock
        when(roomService.createRoom(any(Member.class), any(CreateRoomRequest.class))).thenReturn(1L);

        // given
        final String uri = "/api/v1/rooms";

        // when
        final ResultActions perform = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(roomRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @DisplayName("방 생성 -> 실패")
    @Test
    void createRoomFail() throws Exception {
        // given
        final String uri = "/api/v1/rooms";

        // when
        final ResultActions perform = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(
                                CreateRoomRequest.builder()
                                        .title("qqqqqqqqqqqqqqq")
                                        .capacity(10)
                                        .startDate("2022.06.20")
                                        .endDate("2022.06.30")
                                        .colorIdx(1)
                                        .build())
                        ).contentType(MediaType.APPLICATION_JSON)
        );

        final ResultActions perform1 = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(
                                CreateRoomRequest.builder()
                                        .title("자자자")
                                        .capacity(16)
                                        .endDate("2022.06.30")
                                        .startDate("2022.06.30")
                                        .build())
                        ).contentType(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isBadRequest());
        perform1.andExpect(status().isBadRequest());
    }

    @DisplayName("초대코드 검증 - 성공")
    @Test
    void verifyInvitation_success() throws Exception {
        // given
        final String url = "/api/v1/invitations/verification";
        final VerifyInvitationResponse response =verifyInvitationResponse();
        when(roomService.verifyInvitation(any(Member.class), any(VerifyInvitationRequest.class)))
                .thenReturn(response);

        // when
        ResultActions perform = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(objectMapper.writeValueAsString(
                                VerifyInvitationRequest.builder()
                                        .invitationCode("A1B2C3")
                                        .build())
                        ).contentType(MediaType.APPLICATION_JSON)
        );

        // then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity", is(response.getCapacity())))
                .andExpect(jsonPath("$.title", is(response.getTitle())));
    }

    @DisplayName("초대코드 검증 - 실패 (초대코드가 6자가 아닌 경우)")
    @Test
    void veriyInvitation_fail() throws Exception {
        // given
        final String url = "/api/v1/invitations/verification";

        // when
        ResultActions perform = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(objectMapper.writeValueAsString(
                                VerifyInvitationRequest.builder()
                                        .invitationCode("1231234")
                                        .build()
                        )).contentType(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isBadRequest());
    }

    private CreateRoomRequest roomRequest() {
        return CreateRoomRequest.builder()
                .title("title")
                .capacity(10)
                .startDate("2022.06.20")
                .endDate("2022.06.30")
                .build();
    }

    private VerifyInvitationResponse verifyInvitationResponse() {
        return VerifyInvitationResponse.builder()
                .id(1L)
                .title("제목")
                .capacity(10)
                .startDate("2022.06.20")
                .endDate("2022.06.30")
                .build();

    }

}
package com.firefighter.aenitto.rooms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firefighter.aenitto.rooms.dto.RoomRequest;
import com.firefighter.aenitto.rooms.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
//@SpringBootTest
@AutoConfigureMockMvc // -> webAppContextSetup(webApplicationContext)
@AutoConfigureRestDocs
class RoomControllerTest {
    @InjectMocks
    RoomController roomController;

    @Mock @Qualifier("roomServiceImpl")
    RoomService roomService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;



    @BeforeEach
    void init(RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        objectMapper = new ObjectMapper();
    }

    @DisplayName("방 생성 -> 성공")
    @Test
    void createRoom() throws Exception {
        // Mock
        when(roomService.createRoom(any(UUID.class), any(RoomRequest.class))).thenReturn(roomRequest().toEntity());

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
                .andExpect(header().exists("Location"))
                .andDo(document("room-create", // 5
                        preprocessRequest(prettyPrint()),
                        requestFields(
                                fieldWithPath("title").description("Post 제목"), // 7
                                fieldWithPath("capacity").description("Post 내용").optional(), // 8
                                fieldWithPath("startDate").description("시작일").optional(),
                                fieldWithPath("endDate").description("마지막일")
                        )
                ));
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
                                RoomRequest.builder()
                                        .title("qqqqqqqqq")
                                        .capacity(10)
                                        .startDate("2022.06.20")
                                        .endDate("2022.06.30")
                                        .build())
                        ).contentType(MediaType.APPLICATION_JSON)
        );

        final ResultActions perform1 = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(
                                RoomRequest.builder()
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

    private RoomRequest roomRequest() {
        return RoomRequest.builder()
                .title("title")
                .capacity(10)
                .startDate("2022.06.20")
                .endDate("2022.06.30")
                .build();
    }

}
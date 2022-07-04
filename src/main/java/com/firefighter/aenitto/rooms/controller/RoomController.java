package com.firefighter.aenitto.rooms.controller;

import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.rooms.dto.RoomRequest;
import com.firefighter.aenitto.rooms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {
    @Qualifier("roomServiceImpl")
    private final RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity createRoom(
            @Valid @RequestBody final RoomRequest roomRequest
    ) {
        final UUID memberId = UUID.randomUUID();
        final Room room = roomService.createRoom(memberId, roomRequest);
        return ResponseEntity.created(URI.create("/api/v1/rooms/" + room.getId())).build();
    }
}

package com.firefighter.aenitto.rooms.service;

import com.firefighter.aenitto.members.domain.Member;
import com.firefighter.aenitto.rooms.dto.request.CreateRoomRequest;
import com.firefighter.aenitto.rooms.dto.request.VerifyInviationRequest;
import com.firefighter.aenitto.rooms.dto.response.VerifyInvitationResponse;

public interface RoomService {
    public Long createRoom(Member member, CreateRoomRequest createRoomRequest);

    public VerifyInvitationResponse verifyInvitation(Member member, VerifyInviationRequest verifyInviationRequest);
}

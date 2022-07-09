package com.firefighter.aenitto.rooms.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(force = true)
public class VerifyInviationRequest {
    @NotNull
    private final String invitationCode;

    @Builder
    public VerifyInviationRequest(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
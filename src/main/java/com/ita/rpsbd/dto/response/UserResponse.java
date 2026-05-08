package com.ita.rpsbd.dto.response;

import com.ita.rpsbd.model.User;

import java.util.UUID;

public record UserResponse(UUID id,
                           String name,
                           DeviceResponse device) {

    public UserResponse(User user) {
        this(user.getId(),
                user.getName(),
                user.getDevice() != null ? new DeviceResponse(user.getDevice()) : null);
    }
}

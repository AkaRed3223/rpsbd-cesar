package com.ita.rpsbd.dto.request;

import com.ita.rpsbd.model.DeviceType;

public record UpdateUserRequest(String name,
                                String deviceImei,
                                DeviceType deviceType) {}

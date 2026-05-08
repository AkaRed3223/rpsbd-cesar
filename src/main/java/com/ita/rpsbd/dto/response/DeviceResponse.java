package com.ita.rpsbd.dto.response;

import com.ita.rpsbd.model.Device;
import com.ita.rpsbd.model.DeviceType;

import java.util.UUID;

public record DeviceResponse(UUID id,
                             String imei,
                             DeviceType type) {

    public DeviceResponse(Device device) {
        this(device.getId(), device.getImei(), device.getType());
    }
}

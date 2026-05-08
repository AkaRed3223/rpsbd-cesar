package com.ita.rpsbd.dto.response;

import com.ita.rpsbd.model.Satellite;

import java.math.BigDecimal;
import java.util.UUID;

public record SatelliteResponse(UUID id, String name, BigDecimal latitude, BigDecimal longitude) {

    public SatelliteResponse(Satellite satellite) {
        this(satellite.getId(), satellite.getName(), satellite.getLatitude(), satellite.getLongitude());
    }
}

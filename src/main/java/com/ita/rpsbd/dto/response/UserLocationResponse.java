package com.ita.rpsbd.dto.response;

import com.ita.rpsbd.model.UserLocation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record UserLocationResponse(UUID id,
                                   UUID userId,
                                   BigDecimal latitude,
                                   BigDecimal longitude,
                                   Instant reportedAt,
                                   UUID satelliteId) {

    public UserLocationResponse(UserLocation loc) {
        this(loc.getId(),
                loc.getUser().getId(),
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getReportedAt(),
                loc.getSatellite() != null ? loc.getSatellite().getId() : null);
    }
}

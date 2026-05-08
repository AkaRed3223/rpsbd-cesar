package com.ita.rpsbd.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record SatelliteReportRequest(UUID satelliteId,
                                     UUID userId,
                                     BigDecimal latitude,
                                     BigDecimal longitude
) {}

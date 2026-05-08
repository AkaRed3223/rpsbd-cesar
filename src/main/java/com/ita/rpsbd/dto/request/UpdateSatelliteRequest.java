package com.ita.rpsbd.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateSatelliteRequest(
        @NotBlank String name,
        @NotNull BigDecimal latitude,
        @NotNull BigDecimal longitude
) {}

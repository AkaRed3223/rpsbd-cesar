package com.ita.rpsbd.service;

import com.ita.rpsbd.model.Satellite;
import com.ita.rpsbd.model.UserLocation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Abstracts all satellite interactions.
 * Swap the implementation (e.g. DatabaseSatelliteService → RealSatelliteService)
 * without touching controllers or any other layer.
 */
public interface SatelliteService {
    UserLocation reportLocation(UUID satelliteId, UUID userId, BigDecimal latitude, BigDecimal longitude);
    Satellite register(String name, BigDecimal latitude, BigDecimal longitude);
    List<Satellite> findAll();
    Optional<Satellite> findById(UUID id);
    Optional<Satellite> update(UUID id, String name, BigDecimal latitude, BigDecimal longitude);
}

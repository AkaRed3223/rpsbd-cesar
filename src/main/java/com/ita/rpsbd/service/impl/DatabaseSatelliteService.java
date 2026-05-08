package com.ita.rpsbd.service.impl;

import com.ita.rpsbd.model.Satellite;
import com.ita.rpsbd.model.UserLocation;
import com.ita.rpsbd.repository.SatelliteRepository;
import com.ita.rpsbd.repository.UserLocationRepository;
import com.ita.rpsbd.repository.UserRepository;
import com.ita.rpsbd.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatabaseSatelliteService implements SatelliteService {

    private final SatelliteRepository satelliteRepository;
    private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;

    @Override
    @Transactional
    public UserLocation reportLocation(UUID satelliteId, UUID userId, BigDecimal latitude, BigDecimal longitude) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        var satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new IllegalArgumentException("Satellite not found: " + satelliteId));

        return userLocationRepository.save(
                new UserLocation(user, satellite, latitude, longitude, Instant.now())
        );
    }

    @Override
    public Satellite register(String name, BigDecimal latitude, BigDecimal longitude) {
        return satelliteRepository.save(new Satellite(name, latitude, longitude));
    }

    @Override
    public List<Satellite> findAll() {
        return satelliteRepository.findAll();
    }

    @Override
    public Optional<Satellite> findById(UUID id) {
        return satelliteRepository.findById(id);
    }

    @Override
    public Optional<Satellite> update(UUID id, String name, BigDecimal latitude, BigDecimal longitude) {
        return satelliteRepository.findById(id)
                .map(existing -> satelliteRepository.save(new Satellite(id, name, latitude, longitude)));
    }


    @Override
    public boolean delete(UUID id) {
        if (!satelliteRepository.existsById(id)) return false;
        satelliteRepository.deleteById(id);
        return true;
    }
}

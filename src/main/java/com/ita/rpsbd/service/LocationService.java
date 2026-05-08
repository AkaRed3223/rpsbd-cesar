package com.ita.rpsbd.service;

import com.ita.rpsbd.model.UserLocation;
import com.ita.rpsbd.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final UserLocationRepository userLocationRepository;

    public Optional<UserLocation> getLastKnownLocation(UUID userId) {
        return userLocationRepository.findTopByUser_IdOrderByReportedAtDesc(userId);
    }

    public Page<UserLocation> getLocationHistory(UUID userId, Pageable pageable) {
        return userLocationRepository.findAllByUser_IdOrderByReportedAtDesc(userId, pageable);
    }
}

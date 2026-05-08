package com.ita.rpsbd.repository;

import com.ita.rpsbd.model.UserLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserLocationRepository extends JpaRepository<UserLocation, UUID> {
    Optional<UserLocation> findTopByUser_IdOrderByReportedAtDesc(UUID userId);
    Page<UserLocation> findAllByUser_IdOrderByReportedAtDesc(UUID userId, Pageable pageable);
}

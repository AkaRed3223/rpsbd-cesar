package com.ita.rpsbd.controller;

import com.ita.rpsbd.dto.response.PagedResponse;
import com.ita.rpsbd.dto.response.UserLocationResponse;
import com.ita.rpsbd.model.UserLocation;
import com.ita.rpsbd.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/last")
    public ResponseEntity<UserLocationResponse> getLastLocation(@PathVariable UUID userId) {
        return locationService.getLastKnownLocation(userId)
                .map(UserLocationResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UserLocationResponse>> getLocationHistory(@PathVariable UUID userId, Pageable pageable) {
        Page<UserLocation> page = locationService.getLocationHistory(userId, pageable);
        return ResponseEntity.ok(new PagedResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
                        .stream()
                        .map(UserLocationResponse::new)
                        .toList()
        ));
    }
}

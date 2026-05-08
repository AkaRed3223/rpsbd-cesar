package com.ita.rpsbd.controller;

import com.ita.rpsbd.dto.request.CreateSatelliteRequest;
import com.ita.rpsbd.dto.request.SatelliteReportRequest;
import com.ita.rpsbd.dto.request.UpdateSatelliteRequest;
import com.ita.rpsbd.dto.response.SatelliteResponse;
import com.ita.rpsbd.dto.response.UserLocationResponse;
import com.ita.rpsbd.service.SatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/satellites")
@RequiredArgsConstructor
public class SatelliteController {

    private final SatelliteService satelliteService;

    @GetMapping
    public ResponseEntity<List<SatelliteResponse>> getAll() {
        return ResponseEntity.ok(satelliteService
                .findAll()
                .stream()
                .map(SatelliteResponse::new)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteResponse> getById(@PathVariable UUID id) {
        return satelliteService.findById(id)
                .map(SatelliteResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SatelliteResponse> register(@Valid @RequestBody CreateSatelliteRequest req) {
        return ResponseEntity
                .status(201)
                .body(new SatelliteResponse(satelliteService.register(req.name(), req.latitude(), req.longitude())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateSatelliteRequest req) {
        return satelliteService.update(id, req.name(), req.latitude(), req.longitude())
                .map(SatelliteResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return satelliteService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/report")
    public ResponseEntity<UserLocationResponse> report(@RequestBody SatelliteReportRequest req) {
        return ResponseEntity
                .status(201)
                .body(new UserLocationResponse(
                        satelliteService.reportLocation(
                                req.satelliteId(),
                                req.userId(),
                                req.latitude(),
                                req.longitude()
                        )
                ));
    }
}

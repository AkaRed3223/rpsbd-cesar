package com.ita.rpsbd.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_locations", indexes = @Index(name = "idx_user_locations_user_reported", columnList = "user_id, reported_at DESC"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(nullable = false)
    private Instant reportedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "satellite_id")
    private Satellite satellite;

    public UserLocation(User user, Satellite satellite, BigDecimal latitude, BigDecimal longitude, Instant reportedAt) {
        this.user = user;
        this.satellite = satellite;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reportedAt = reportedAt;
    }
}

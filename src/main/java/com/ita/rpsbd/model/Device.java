package com.ita.rpsbd.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String imei;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;

    public Device(String imei, DeviceType type) {
        this.imei = imei;
        this.type = type;
    }

    public Device(UUID id, String imei, DeviceType type) {
        this.id = id;
        this.imei = imei;
        this.type = type;
    }
}

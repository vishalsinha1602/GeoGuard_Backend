package com.backend.geosentinel.geofence.entity;

import com.backend.geosentinel.devices.entity.Device;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "geofences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Geofence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the geofence
     * Example: Home, Office, College
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Center latitude
     */
    @Column(nullable = false)
    private Double latitude;

    /**
     * Center longitude
     */
    @Column(nullable = false)
    private Double longitude;

    /**
     * Radius in meters
     */
    @Column(nullable = false)
    private Double radius;


    /**
     * Fence enabled or disabled
     */
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    /**
     * Device to which this geofence belongs
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
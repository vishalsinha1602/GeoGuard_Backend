package com.backend.geosentinel.devices.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "locations",
        indexes = {
                @Index(name = "idx_device", columnList = "device_id"),
                @Index(name = "idx_received_at", columnList = "receivedAt")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Latitude
     * Example: 28.613939
     */
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude;


//     * Longitude
//     * Example: 77.209021

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude;


//     * Speed in km/h

    private Double speed;


//     * Direction (0-360°)

    private Double heading;


//     * GPS accuracy in meters

    private Double accuracy;


//     * Altitude in meters

    private Double altitude;


//     * Time when device captured this location

    private LocalDateTime deviceTimestamp;


//     * Time when backend stored it

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime receivedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;


}
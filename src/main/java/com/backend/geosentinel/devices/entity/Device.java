package com.backend.geosentinel.devices.entity;

import com.backend.geosentinel.devices.entity.enums.DeviceStatus;
import com.backend.geosentinel.devices.entity.enums.DeviceType;
import com.backend.geosentinel.locations.entity.Location;
import com.backend.geosentinel.security.entity.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Public identifier exposed through APIs.
     * Never expose the database id.
     */
    @Builder.Default
    @Column(nullable = false, unique = true, updatable = false)
    private UUID publicId = UUID.randomUUID();


    @NotBlank
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceStatus status;

    @Column(nullable = false)

    @Min(0)
    @Max(100)
    private Integer batteryLevel;

    private LocalDateTime lastSeen;

    @OneToMany(
            mappedBy = "device",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Location> locations = new ArrayList<>();



    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private boolean active = true;



    @PrePersist
    public void prePersist() {

        if(status == null){
            status = DeviceStatus.OFFLINE;
        }

        if(batteryLevel == null){
            batteryLevel = 100;
        }

        if(lastSeen == null){
            lastSeen = LocalDateTime.now();
        }
    }



}
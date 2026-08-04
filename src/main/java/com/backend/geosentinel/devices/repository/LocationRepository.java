package com.backend.geosentinel.devices.repository;

import com.backend.geosentinel.devices.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}

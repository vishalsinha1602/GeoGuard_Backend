package com.backend.geosentinel.devices.repository;

import com.backend.geosentinel.devices.entity.Device;
import com.backend.geosentinel.devices.entity.Location;
import com.backend.geosentinel.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Integer> {
    List<Device> findByOwner(User currentUser);

    List<Device> findBypublicId(User currentUser);
}

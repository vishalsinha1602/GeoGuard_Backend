package com.backend.geosentinel.devices.service;

import com.backend.geosentinel.devices.dto.DeviceRequestDto;
import com.backend.geosentinel.devices.dto.DeviceResponseDto;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    DeviceResponseDto createDevice(DeviceRequestDto deviceRequestDto);

    List<DeviceResponseDto> getAllDevice();

    DeviceResponseDto getDeviceById(UUID publicId);
}

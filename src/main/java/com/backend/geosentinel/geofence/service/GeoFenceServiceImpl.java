package com.backend.geosentinel.geofence.service;

import com.backend.geosentinel.devices.entity.Device;
import com.backend.geosentinel.devices.repository.DeviceRepository;
import com.backend.geosentinel.exception.ResourceNotFoundException;
import com.backend.geosentinel.geofence.dto.GeoFenceRequestDto;
import com.backend.geosentinel.geofence.dto.GeoFenceResponseDto;
import com.backend.geosentinel.geofence.entity.Geofence;
import com.backend.geosentinel.geofence.repository.GeofenceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeoFenceServiceImpl implements GeofenceService {

    private final GeofenceRepository geofenceRepository;
    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;

    @Override
    public GeoFenceResponseDto createGeofence(
            GeoFenceRequestDto requestDto) {

        Device device = deviceRepository
                .findByPublicId(requestDto.getDevicePublicId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found"));

        Geofence geofence = Geofence.builder()
                .name(requestDto.getName())
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .radius(requestDto.getRadius())
                .device(device)
                .build();

        geofence.setDevice(device);

        Geofence saved =
                geofenceRepository.save(geofence);

        GeoFenceResponseDto response =
                modelMapper.map(saved, GeoFenceResponseDto.class);

        response.setDevicePublicId(saved.getDevice().getPublicId());

        return response;
    }



    @Override
    public List<GeoFenceResponseDto> getDeviceGeofences(
            UUID devicePublicId) {

        return geofenceRepository
                .findByDevice_PublicId(devicePublicId)
                .stream()
                .map(geofence -> {

                    GeoFenceResponseDto dto =
                            modelMapper.map(
                                    geofence,
                                    GeoFenceResponseDto.class);

                    dto.setDevicePublicId(geofence.getDevice().getPublicId());
                    return dto;
                })
                .toList();
    }



//    @Override
//    public GeoFenceResponseDto getGeofenceById(
//            Long id) {
//
//        Geofence geofence =
//                geofenceRepository.findById(id)
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("Geofence not found"));
//
//        GeoFenceResponseDto dto =
//                modelMapper.map(
//                        geofence,
//                        GeoFenceResponseDto.class);
//
//        dto.setDevicePublicId(
//                geofence.getDevice().getPublicId());
//
//        return dto;
//    }



    @Override
    public void deleteGeofence(
            Long id) {

        Geofence geofence =
                geofenceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Geofence not found"));

        geofenceRepository.delete(geofence);
    }
}
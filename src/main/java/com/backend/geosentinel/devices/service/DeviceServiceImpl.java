package com.backend.geosentinel.devices.service;

import com.backend.geosentinel.devices.dto.DeviceRequestDto;
import com.backend.geosentinel.devices.dto.DeviceResponseDto;
import com.backend.geosentinel.devices.entity.Device;
import com.backend.geosentinel.devices.repository.DeviceRepository;
import com.backend.geosentinel.exception.UnAuthorisedException;
import com.backend.geosentinel.security.entity.User;
import com.backend.geosentinel.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private final ModelMapper modelMapper;
    private final DeviceRepository deviceRepository;


    @Override
    public DeviceResponseDto createDevice(DeviceRequestDto deviceRequestDto) {
        User currentUser = AppUtil.getCurrentUser();





        Device device = modelMapper.map(deviceRequestDto, Device.class);


        device.setOwner(currentUser);

        Device savedDevice = deviceRepository.save(device);

        return modelMapper.map(savedDevice, DeviceResponseDto.class);
    }

    @Override
    public List<DeviceResponseDto> getAllDevice() {
        User currentUser = AppUtil.getCurrentUser();



        List<Device> devices = deviceRepository.findByOwner(currentUser);

        return devices.stream()
                .map(device -> modelMapper.map(device, DeviceResponseDto.class))
                .toList();
    }

    @Override
    public DeviceResponseDto getDeviceById(UUID publicId) {
        User currentUser = AppUtil.getCurrentUser();
        List<Device> devices = deviceRepository.findBypublicId(currentUser);
        return modelMapper.map(devices, DeviceResponseDto.class);
    }
}

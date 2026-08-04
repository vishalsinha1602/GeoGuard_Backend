package com.backend.geosentinel.devices.dto;


import com.backend.geosentinel.devices.entity.enums.DeviceType;
import lombok.Data;

@Data
public class DeviceRequestDto {

    private String name;
    private DeviceType type;

}

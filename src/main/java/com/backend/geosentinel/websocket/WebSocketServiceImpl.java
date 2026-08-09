package com.backend.geosentinel.websocket;

import com.backend.geosentinel.devices.dto.DeviceLiveDto;
import com.backend.geosentinel.locations.dto.LocationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendLocationUpdate(
            UUID devicePublicId,
            DeviceLiveDto response
    ) {

        log.info("Sending location update for device {}", devicePublicId);

        messagingTemplate.convertAndSend(
                "/topic/location/" + devicePublicId,
                response
        );
    }
}
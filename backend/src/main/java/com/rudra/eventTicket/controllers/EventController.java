package com.rudra.eventTicket.controllers;

import com.rudra.eventTicket.domain.CreateEventRequest;
import com.rudra.eventTicket.domain.dto.CreateEventRequestDto;
import com.rudra.eventTicket.domain.dto.CreateEventResponseDto;
import com.rudra.eventTicket.domain.entities.Event;
import com.rudra.eventTicket.mappers.EventMapper;
import com.rudra.eventTicket.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto
            ){
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = UUID.fromString(jwt.getSubject());

        Event event = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(event);
        return new ResponseEntity<>(createEventResponseDto,HttpStatus.CREATED);
    }
}

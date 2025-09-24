package com.rudra.eventTicket.mappers;

import com.rudra.eventTicket.domain.CreateEventRequest;
import com.rudra.eventTicket.domain.CreateTicketTypeRequest;
import com.rudra.eventTicket.domain.dto.CreateEventRequestDto;
import com.rudra.eventTicket.domain.dto.CreateEventResponseDto;
import com.rudra.eventTicket.domain.dto.CreateTicketTypeRequestDto;
import com.rudra.eventTicket.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}

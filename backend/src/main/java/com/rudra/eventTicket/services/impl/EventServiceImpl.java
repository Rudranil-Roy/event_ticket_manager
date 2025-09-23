package com.rudra.eventTicket.services.impl;

import com.rudra.eventTicket.domain.CreateEventRequest;
import com.rudra.eventTicket.domain.entities.Event;
import com.rudra.eventTicket.domain.entities.TicketType;
import com.rudra.eventTicket.domain.entities.User;
import com.rudra.eventTicket.exceptions.UserNotFoundException;
import com.rudra.eventTicket.repositories.EventRepository;
import com.rudra.eventTicket.repositories.UserRepository;
import com.rudra.eventTicket.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerID, CreateEventRequest event) {

        User organizer = userRepository.findById(organizerID).orElseThrow(
                () -> new UserNotFoundException("User not found with id " + organizerID)
        );

        List<TicketType> ticketTypes = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    return ticketTypeToCreate;
                }
        ).toList();

        Event eventToBeCreated = new Event();
        eventToBeCreated.setOrganizer(organizer);
        eventToBeCreated.setName(event.getName());
        eventToBeCreated.setVenue(event.getVenue());
        eventToBeCreated.setSalesStart(event.getSalesStart());
        eventToBeCreated.setSalesEnd(event.getSalesEnd());
        eventToBeCreated.setStartTime(event.getStartTime());
        eventToBeCreated.setEndTime(event.getEndTime());
        eventToBeCreated.setStatus(event.getStatus());
        eventToBeCreated.setTicketTypes(ticketTypes);

        return eventRepository.save(eventToBeCreated);
    }
}

package com.rudra.eventTicket.services;

import com.rudra.eventTicket.domain.CreateEventRequest;
import com.rudra.eventTicket.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerID, CreateEventRequest createEventRequest);
}

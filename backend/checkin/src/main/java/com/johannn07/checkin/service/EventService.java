package com.johannn07.checkin.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.johannn07.checkin.dto.EventResponse;
import com.johannn07.checkin.entity.Event;
import com.johannn07.checkin.repository.EventRepository;
import com.johannn07.checkin.repository.RegistrationRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    
    public EventService(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<EventResponse> listEvents() {
        return eventRepository.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    public EventResponse getEvent(Long id){
        var event = eventRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with id: " + id + " not found"));
        return toResponse(event);
    }

    EventResponse toResponse(Event event){
        long registered = registrationRepository.countByEventId(event.getId());
        long spotsLeft = Math.max(0, event.getCapacity() - registered);
        return new EventResponse(
            event.getId(),
            event.getName(),
            event.getDescription(),
            event.getVenue(),
            event.getStartsAt(),
            event.getEndsAt(),
            event.getCapacity(),
            registered,
            spotsLeft
        );
    }
}

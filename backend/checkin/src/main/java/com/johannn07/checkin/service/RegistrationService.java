package com.johannn07.checkin.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.johannn07.checkin.dto.RegisterRequest;
import com.johannn07.checkin.dto.TicketResponse;
import com.johannn07.checkin.entity.Registration;
import com.johannn07.checkin.repository.EventRepository;
import com.johannn07.checkin.repository.RegistrationRepository;

import org.springframework.transaction.annotation.Transactional;

@Service 
public class RegistrationService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public TicketResponse register(Long eventId, RegisterRequest request) {
        var event = eventRepository.findByIdForUpdate(eventId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with id: " + eventId + " not found"));
        
        String email = request.email().trim().toLowerCase();   

        if (registrationRepository.existsByEventIdAndEmail(eventId, email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered for this event");
        }

        if (registrationRepository.countByEventId(eventId) >= event.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Event is full");
        }

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setFullName(request.fullName().trim());
        registration.setEmail(email);
        registration.setMobile(request.mobile().trim());
        registration.setTicketCode(UUID.randomUUID().toString());
        registrationRepository.save(registration);
    
        return new TicketResponse(
            registration.getTicketCode(),
            registration.getFullName(),
            event.getName(),
            event.getStartsAt()
        );
    }
}

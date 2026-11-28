package com.johannn07.checkin.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.johannn07.checkin.dto.TicketResponse;
import com.johannn07.checkin.repository.RegistrationRepository;

@Service
public class CheckinService {
    private static final DateTimeFormatter TIME_FMT =
        DateTimeFormatter.ofPattern("MMM d, h:mm a").withZone(ZoneId.of("Asia/Manila"));

    private final RegistrationRepository registrationRepository;

    public CheckinService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public TicketResponse checkIn(String ticketCode) {
        var registration = registrationRepository.findByTicketCodeForUpdate(ticketCode.trim())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        if (registration.getCheckedInAt() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Already checked in at " + TIME_FMT.format(registration.getCheckedInAt()));
        }

        registration.setCheckedInAt(OffsetDateTime.now());
        // no save() needed: the entity is managed, so the change is flushed on commit

        var event = registration.getEvent();
        return new TicketResponse(
            registration.getTicketCode(),
            registration.getFullName(),
            event.getName(),
            event.getStartsAt(),
            registration.getCheckedInAt()
        );
    }
}
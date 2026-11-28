package com.johannn07.checkin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.johannn07.checkin.dto.RegisterRequest;
import com.johannn07.checkin.dto.TicketResponse;
import com.johannn07.checkin.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/events/{eventId}/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse register(@PathVariable Long eventId, @Valid @RequestBody RegisterRequest request) {
        return registrationService.register(eventId, request);
    }
    
}

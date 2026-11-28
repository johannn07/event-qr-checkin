package com.johannn07.checkin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johannn07.checkin.dto.TicketResponse;
import com.johannn07.checkin.service.RegistrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/api/tickets")
public class TicketController {
    private final RegistrationService registrationService;

    public TicketController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/{ticketCode}")
    public TicketResponse getTicket(@PathVariable String ticketCode) {
        return registrationService.getTicket(ticketCode);
    }
    
}

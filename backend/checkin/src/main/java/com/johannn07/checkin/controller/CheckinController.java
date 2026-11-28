package com.johannn07.checkin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johannn07.checkin.dto.CheckinRequest;
import com.johannn07.checkin.dto.TicketResponse;
import com.johannn07.checkin.service.CheckinService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/checkin")
public class CheckinController {
    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @PostMapping
    public TicketResponse checkIn(@Valid @RequestBody CheckinRequest request) {
        return checkinService.checkIn(request.ticketCode());
    }
}

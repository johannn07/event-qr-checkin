package com.johannn07.checkin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johannn07.checkin.dto.EventResponse;
import com.johannn07.checkin.service.EventService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping ("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventResponse> listEvents() {
        return eventService.listEvents();
    }

    @GetMapping("/{id}")
    public EventResponse getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }
    
}

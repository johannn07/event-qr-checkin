package com.johannn07.checkin.dto;

import jakarta.validation.constraints.NotBlank;

public record CheckinRequest (
    @NotBlank String ticketCode
){}

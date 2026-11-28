package com.johannn07.checkin.dto;

import java.time.OffsetDateTime;

public record AttendeeResponse(
    String fullName, 
    String email, 
    String mobile,
    String ticketCode,
    OffsetDateTime checkedInAt,
    OffsetDateTime createdAt
) {}

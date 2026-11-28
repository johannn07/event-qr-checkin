package com.johannn07.checkin.dto;

import java.time.OffsetDateTime;

public record TicketResponse(
    String ticketCode,
    String fullName,
    String eventName,
    OffsetDateTime startsAt,
    OffsetDateTime checkedInAt
) {}

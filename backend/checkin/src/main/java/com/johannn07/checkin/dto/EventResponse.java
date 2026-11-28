package com.johannn07.checkin.dto;

import java.time.OffsetDateTime;

public record EventResponse(
    Long id,
    String name,
    String description,
    String venue,
    OffsetDateTime startsAt,
    OffsetDateTime endsAt,
    int capacity,
    long registered,
    long spotsLeft
) {
}
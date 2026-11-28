package com.johannn07.checkin.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter 
@Setter 
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String venue;
    private OffsetDateTime startsAt;
    private OffsetDateTime endsAt;
    private int capacity;

    @Column(insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}

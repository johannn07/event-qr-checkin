-- V1: initial schema for Event Registration with QR Check-in

-- Events an organizer creates
CREATE TABLE events (
    id          BIGSERIAL    PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    description TEXT,
    venue       VARCHAR(200),
    starts_at   TIMESTAMPTZ  NOT NULL,
    ends_at     TIMESTAMPTZ,
    capacity    INT          NOT NULL CHECK (capacity > 0),
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CHECK (ends_at IS NULL OR ends_at > starts_at)
);

-- One row per attendee per event; ticket_code is what the QR encodes
CREATE TABLE registrations (
    id            BIGSERIAL    PRIMARY KEY,
    event_id      BIGINT       NOT NULL REFERENCES events (id),
    full_name     VARCHAR(150) NOT NULL,
    email         VARCHAR(254) NOT NULL,
    mobile        VARCHAR(11)  NOT NULL CHECK (mobile ~ '^09[0-9]{9}$'),
    ticket_code   VARCHAR(64)  NOT NULL UNIQUE,
    checked_in_at TIMESTAMPTZ,              -- NULL = not checked in yet
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT uq_registrations_event_email UNIQUE (event_id, email)
);

CREATE INDEX idx_registrations_event_id ON registrations (event_id);

-- Organizers and door staff who log in (passwords stored as BCrypt hashes)
CREATE TABLE staff_users (
    id            BIGSERIAL    PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    role          VARCHAR(20)  NOT NULL CHECK (role IN ('ORGANIZER', 'STAFF')),
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now()
);

-- Demo event (times in Asia/Manila, UTC+08)
INSERT INTO events (name, description, venue, starts_at, ends_at, capacity)
VALUES (
    'Cebu Dev Meetup 2026',
    'Community meetup for Cebu developers.',
    'Cebu City',
    '2026-12-12 18:00+08',
    '2026-12-12 21:00+08',
    30
);

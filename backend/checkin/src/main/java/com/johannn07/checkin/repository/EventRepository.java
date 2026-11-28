package com.johannn07.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johannn07.checkin.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}

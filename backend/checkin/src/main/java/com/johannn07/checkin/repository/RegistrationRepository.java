package com.johannn07.checkin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johannn07.checkin.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByEventIdAndEmail(Long eventId, String email);
    long countByEventId(Long eventId);
    Optional<Registration> findByTicketCode(String ticketCode);
    List<Registration> findByEventIdOrderByCreatedAtAsc(Long eventId);
}

package com.johannn07.checkin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.johannn07.checkin.entity.Registration;

import jakarta.persistence.LockModeType;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByEventIdAndEmail(Long eventId, String email);
    long countByEventId(Long eventId);
    Optional<Registration> findByTicketCode(String ticketCode);
    List<Registration> findByEventIdOrderByCreatedAtAsc(Long eventId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Registration r WHERE r.ticketCode = :code")
    Optional<Registration> findByTicketCodeForUpdate(@Param("code") String code);
}

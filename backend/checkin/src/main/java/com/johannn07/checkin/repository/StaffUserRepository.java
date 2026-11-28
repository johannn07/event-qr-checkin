package com.johannn07.checkin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johannn07.checkin.entity.StaffUser;

public interface StaffUserRepository extends JpaRepository<StaffUser, Long> {
    Optional<StaffUser> findByUsername(String username);
}

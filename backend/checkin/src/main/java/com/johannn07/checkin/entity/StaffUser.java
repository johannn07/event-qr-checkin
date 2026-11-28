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
@Table (name = "staff_users")
@Getter 
@Setter 
public class StaffUser {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwordHash;
    private String role;

    @Column (insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}

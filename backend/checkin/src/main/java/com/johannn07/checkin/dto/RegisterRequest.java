package com.johannn07.checkin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank @Size(max=150) String fullName,
    @NotBlank @Email @Size(max=254) String email,
    @NotBlank @Pattern(regexp = "^09\\d{9}$", message = "Must be 11 digits starting with 09") String mobile
) {}

package com.johannn07.checkin.controller;

import org.springframework.web.bind.annotation.RestController;

import com.johannn07.checkin.dto.MeResponse;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController 
@RequestMapping ("/api/auth")
public class AuthController {
    @GetMapping("/me")
    public MeResponse me(Authentication auth) {
        String role = auth.getAuthorities().iterator().next()
            .getAuthority().replace("ROLE_", "");
        return new MeResponse(auth.getName(), role);
    }
}

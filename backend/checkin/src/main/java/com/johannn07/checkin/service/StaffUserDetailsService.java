package com.johannn07.checkin.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.johannn07.checkin.repository.StaffUserRepository;

@Service
public class StaffUserDetailsService implements UserDetailsService {
   private final StaffUserRepository staffUserRepository;

    public StaffUserDetailsService(StaffUserRepository staffUserRepository) {
        this.staffUserRepository = staffUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = staffUserRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.withUsername(user.getUsername())
            .password(user.getPasswordHash())
            .roles("STAFF")
            .build();
    }
}
package com.johannn07.checkin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.johannn07.checkin.entity.StaffUser;
import com.johannn07.checkin.repository.StaffUserRepository;

@Component 
public class StaffSeeder implements CommandLineRunner {
    private final StaffUserRepository staffUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ORGANIZER_USERNAME:}") private String organizerUsername;
    @Value("${ORGANIZER_PASSWORD:}") private String organizerPassword;
    @Value("${STAFF_USERNAME:}") private String staffUsername;
    @Value("${STAFF_PASSWORD:}") private String staffPassword;

    public StaffSeeder(StaffUserRepository staffUserRepository, PasswordEncoder passwordEncoder) {
        this.staffUserRepository = staffUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        seed(organizerUsername, organizerPassword, "ORGANIZER");
        seed(staffUsername, staffPassword, "STAFF");
    }

    private void seed(String username, String password, String role) {
        if (username.isBlank() || password.isBlank()) {
            return;
        }

        if (staffUserRepository.findByUsername(username).isPresent()) {
            return;
        }

        StaffUser user = new StaffUser();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(role);
        staffUserRepository.save(user);
    }

}

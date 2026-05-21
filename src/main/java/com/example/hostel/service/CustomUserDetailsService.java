//package com.example.hostel.service;
//
//import com.example.hostel.model.User;
//import com.example.hostel.repository.UserRepository;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository repo;
//    public CustomUserDetailsService(UserRepository repo){
//        this.repo = repo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User u = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), Collections.emptyList());
//    }
//}
package com.example.hostel.service;

import com.example.hostel.model.User;
import com.example.hostel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Hardcoded admin password (encoded once)
    private final String adminPassword;

    public CustomUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
        this.adminPassword = encoder.encode("admin123");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ✅ Admin login
        if ("admin".equalsIgnoreCase(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("admin")
                    .password(adminPassword)
                    .roles("ADMIN")
                    .build();
        }

        // ✅ Normal user login
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // "USER"
                .build();
    }
}


package com.example.bookflixspring.user;

import org.springframework.stereotype.Service;

import com.example.bookflixspring.config.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User updateUserRole(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String refreshToken;
        String userEmail;
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null; // Or throw an exception
        }
        
        refreshToken = authorizationHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        User user = userRepository.findByEmail(userEmail);

        if (user != null) {
            // Toggle the user's role
            if (Role.ADMIN.equals(user.getRole())) {
                user.setRole(Role.USER);
            } else {
                user.setRole(Role.ADMIN);
            }

            // Save the updated user back to the database and return it
            return userRepository.save(user);
        }

        // Return null or throw an exception if the user is not found
        return null;
    }
}

package com.example.bookflixspring.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    
    @PutMapping("/role")
    public User updateUserRole(HttpServletRequest request) {
        return userService.updateUserRole(request);
    }
}

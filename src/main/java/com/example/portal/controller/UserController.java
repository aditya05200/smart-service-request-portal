package com.example.portal.controller;

import com.example.portal.dto.UserRequestDTO;
import com.example.portal.dto.UserResponseDTO;
import com.example.portal.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return userService.register(dto);
    }
}
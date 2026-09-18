package com.skr1l.minierp.controller;

import com.skr1l.minierp.dto.RegisterRequestDto;
import com.skr1l.minierp.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(
            @Valid @RequestBody RegisterRequestDto registerDto
    ) {
        Long userId = authService.register(registerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userId);
    }
}
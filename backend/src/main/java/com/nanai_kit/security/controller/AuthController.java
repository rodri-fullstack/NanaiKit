package com.nanai_kit.security.controller;

import com.nanai_kit.security.dto.AuthRequest;
import com.nanai_kit.security.dto.AuthResponse;
import com.nanai_kit.security.dto.RegisterRequest;
import com.nanai_kit.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
    "https://nanai-kit.vercel.app",
    "https://nanai-kit-git-main-nanai-kit.vercel.app",
    "https://nanai-kit-git-develop-nanai-kit.vercel.app",
    "http://localhost:3000",
    "http://localhost:5173"
}, allowCredentials = "true")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }
}

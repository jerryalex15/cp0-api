package com.example.cp0_api.controller;

import com.example.cp0_api.dto.GoogleLoginRequest;
import com.example.cp0_api.dto.LoginRequest;
import com.example.cp0_api.dto.SignInRequest;
import com.example.cp0_api.dto.TokenResponse;
import com.example.cp0_api.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return  authService.signin(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.loginLocal(request);
    }

    @PostMapping("/google")
    public TokenResponse loginGoogle(@RequestBody GoogleLoginRequest request) {
        return authService.loginWithGoogle(request);
    }
}
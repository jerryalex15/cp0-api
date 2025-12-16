package com.example.cp0_api.service;

import com.example.cp0_api.dto.GoogleLoginRequest;
import com.example.cp0_api.dto.LoginRequest;
import com.example.cp0_api.dto.SignInRequest;
import com.example.cp0_api.dto.TokenResponse;

public interface AuthService {
    public TokenResponse loginLocal(LoginRequest request);
    public TokenResponse loginWithGoogle(GoogleLoginRequest request);

    public TokenResponse signin(SignInRequest request);
}
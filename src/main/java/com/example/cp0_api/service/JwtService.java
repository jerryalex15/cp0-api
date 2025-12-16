package com.example.cp0_api.service;

public interface JwtService {

    // ---------------- Génération du token ----------------
    public String generateToken(String email);

    // ---------------- Récupérer l’email depuis le token ----------------
    public String extractEmail(String token);

    // ---------------- Vérifier la validité du token ----------------
    public boolean isTokenValid(String token) ;
}
package com.example.cp0_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;
}

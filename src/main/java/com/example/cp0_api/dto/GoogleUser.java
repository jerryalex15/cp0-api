package com.example.cp0_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleUser {
    private String sub;
    private String email;
    private boolean email_verified;
    private String name;
    private String givenName;
    private String familyName;
}

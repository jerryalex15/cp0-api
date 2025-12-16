package com.example.cp0_api.service;

import com.example.cp0_api.dto.*;
import com.example.cp0_api.model.Person;
import com.example.cp0_api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonRepository personRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String GOOGLE_TOKEN_INFO_URL = "https://oauth2.googleapis.com/tokeninfo?id_token=";


    /**
     * @param request
     * @return
     */
    @Override
    public TokenResponse signin(SignInRequest request) {
        return null;
    }

    // ---------------- Login classique ----------------
    @Override
    public TokenResponse loginLocal(LoginRequest request) {
        Person user = personRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        // Générer JWT
        String token = jwtService.generateToken(user.getEmail());

        return new TokenResponse(token);
    }

    // ---------------- Login Google ----------------
    @Override
    public TokenResponse loginWithGoogle(GoogleLoginRequest request) {
        // Vérifier le token Google
        GoogleUser googleUser = verifyGoogleToken(request.getIdToken());

        // Chercher ou créer l’utilisateur
        Person user = personRepository.findByEmail(googleUser.getEmail())
                .orElseGet(() -> createUserFromGoogle(googleUser));

        // Générer JWT interne
        String token = jwtService.generateToken(user.getEmail());

        return new TokenResponse(token);
    }

    // ---------------- Créer un utilisateur à partir de Google ----------------
    private Person createUserFromGoogle(GoogleUser googleUser) {
        Person newUser = Person.builder()
                .email(googleUser.getEmail())
                .nom(googleUser.getFamilyName())
                .prenom(googleUser.getGivenName())
                .provider("GOOGLE")
                .providerId(googleUser.getSub())
                .password(null)
                .build();

        return personRepository.save(newUser);
    }

    // ---------------- Vérification du token Google ----------------
    private GoogleUser verifyGoogleToken(String idToken) {
        try {
            GoogleUser googleUser = restTemplate.getForObject(GOOGLE_TOKEN_INFO_URL + idToken, GoogleUser.class);

            if (googleUser == null || !googleUser.isEmail_verified()) {
                throw new RuntimeException("Token Google invalide");
            }

            return googleUser;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la vérification du token Google", e);
        }
    }
}

package com.example.cp0_api.unit.service;

import com.example.cp0_api.dto.SignInRequest;
import com.example.cp0_api.dto.TokenResponse;
import com.example.cp0_api.model.Person;
import com.example.cp0_api.repository.PersonRepository;
import com.example.cp0_api.service.AuthServiceImpl;
import com.example.cp0_api.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    PersonRepository personRepository;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void givenUserInformation_whenSignIn_thenReturnToken(){
        // Given
        SignInRequest signInRequest = SignInRequest.builder()
                .nom("Alice")
                .prenom("Morgan")
                .email("alice@gmail.fr").build();
        Person savedPerson = Person.builder()
                .idPersonne(1L)
                .nom("Alice")
                .prenom("Morgan")
                .email("alice@gmail.fr").build();

        Mockito.when(personRepository.save(Mockito.any(Person.class)))
                .thenReturn(savedPerson);
        Mockito.when(jwtService.generateToken(Mockito.anyString()))
                .thenReturn("jwt-token");

        // When
        TokenResponse tokenResponse = authService.signin(signInRequest);

        // Then
        Assertions.assertNotNull(tokenResponse);
        Assertions.assertEquals("jwt-token", tokenResponse.getToken());

        Mockito.verify(personRepository).save(Mockito.any(Person.class));
        Mockito.verify(jwtService).generateToken(Mockito.anyString());
    }
}

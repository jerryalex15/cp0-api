package com.example.cp0_api.integration.service;

import com.example.cp0_api.dto.ThematicRequest;
import com.example.cp0_api.dto.ThematicResponseLight;
import com.example.cp0_api.exception.DuplicateResourceException;
import com.example.cp0_api.service.ThematicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test") // application-test.properties
@Transactional // rollback après chaque test
public class ThematicIntegrationTest {

    @Autowired
    private ThematicService thematicService;

    @Test
    void shouldNotAllowDuplicateThematicInDatabase() {

        // Given
        ThematicRequest request = new ThematicRequest("Loisir");

        // When
        thematicService.create(request);

        // When / Then
        DuplicateResourceException exception = Assertions.assertThrows(
                DuplicateResourceException.class,
                () -> thematicService.create(request)
        );
        Assertions.assertTrue(exception.getMessage().contains("Loisir"));
    }

    @Test
    void shouldCreateThematicSuccessfully(){
        // Given
        ThematicRequest thematicRequest = new ThematicRequest("Loisir");

        // When
        ThematicResponseLight responseLight = thematicService.create(thematicRequest);

        // Then
        Assertions.assertNotNull(responseLight.getNomThematic());
        Assertions.assertEquals("Loisir", responseLight.getNomThematic());
    }
}

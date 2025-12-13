package com.example.cp0_api.serviceTest;

import com.example.cp0_api.dto.ThematicRequest;
import com.example.cp0_api.dto.ThematicResponseBase;
import com.example.cp0_api.dto.ThematicResponseFull;
import com.example.cp0_api.dto.ThematicResponseLight;
import com.example.cp0_api.mapper.IAppMapper;
import com.example.cp0_api.model.Thematic;
import com.example.cp0_api.repository.ThematicRepository;
import com.example.cp0_api.service.ThematicService;
import com.example.cp0_api.service.ThematicServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ThematicServiceTest {
    @Mock
    private ThematicRepository thematicRepository;

    @Mock
    private IAppMapper mapper;

    @InjectMocks
    private ThematicServiceImpl thematicServiceImpl;

    @Test
    void givenValidIdAndIncludeCircuitFalse_whenGetThematic_thenReturnResponseLight(){
        // Given
        Long id = 1L;
        Thematic thematic = new Thematic();
        ThematicResponseLight response = new ThematicResponseLight();

        Mockito.when(thematicRepository.findById(id))
                .thenReturn(Optional.of(thematic));

        Mockito.when(mapper.toThematicResponseLight(thematic))
                .thenReturn(response);

        // When
        ThematicResponseBase result = thematicServiceImpl.getThematic(id, false);

        //Then
        Assertions.assertEquals(response, result); // on verra si ThematicResponseBase peut être comparé à ThematicResponseLight
        Mockito.verify(mapper).toThematicResponseLight(thematic);
        Mockito.verify(mapper, Mockito.never()).toThematicResponseFull(Mockito.any());
    }
    
    @Test
    void givenValidIdAndIncludeCircuitTrue_whenGetThematic_thenReturnResponseFull(){
        // Given
        Long id = 1L;
        Thematic thematic = new Thematic();
        thematic.setIdThematic(1L);
        ThematicResponseFull responseFull = new ThematicResponseFull();
        responseFull.setIdThematic(1L);

        Mockito.when(thematicRepository.findById(id))
                .thenReturn(Optional.of(thematic));

        Mockito.when(mapper.toThematicResponseFull(thematic))
                .thenReturn(responseFull);

        // When
        ThematicResponseBase result = thematicServiceImpl.getThematic(id, true);

        // Then
        Assertions.assertEquals(result, responseFull);
        Mockito.verify(thematicRepository).findById(id);
        Mockito.verify(mapper).toThematicResponseFull(thematic);
        Mockito.verify(mapper, Mockito.never()).toThematicResponseLight(Mockito.any());
    }

    @Test
    void givenInvalidId_whenGetThematic_thenReturnException(){
        // Given
        Long id = 0L;

        Mockito.when(thematicRepository.findById(id))
                .thenReturn(Optional.empty());

        // When
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () ->
                thematicServiceImpl.getThematic(id, true));

        // Then
        Assertions.assertTrue(ex.getMessage().contains("Thematic with id : "+ id +" not found"));
    }

    @Test
    void givenThematicRequest_whenCreateThematic_thenReturnThematicResponseLight(){
        // Given
        ThematicRequest thematicRequest = new ThematicRequest("Loisir");

        // Crée l'entité attendue avant sauvegarde
        Thematic thematicToSave = new Thematic();
        thematicToSave.setNomThematic(thematicRequest.getNomThematic());

        // Ce que save() doit retourner
        Thematic savedThematic = new Thematic();
        savedThematic.setIdThematic(1L);
        savedThematic.setNomThematic(thematicRequest.getNomThematic());

        // Crée le DTO attendu
        ThematicResponseLight responseLight = new ThematicResponseLight();
        responseLight.setIdThematic(savedThematic.getIdThematic());
        responseLight.setNomThematic(savedThematic.getNomThematic());

        // Mock du repository
        Mockito.when(thematicRepository.save(Mockito.any(Thematic.class)))
                .thenReturn(savedThematic);
        Mockito.when(mapper.toThematicResponseLight(Mockito.any(Thematic.class)))
                .thenReturn(responseLight);

        // When
        ThematicResponseLight createdThematic = thematicServiceImpl.create(thematicRequest);

        // Then
        Assertions.assertNotNull(createdThematic);
        Mockito.verify(thematicRepository).save(Mockito.any(Thematic.class));
        Assertions.assertEquals(createdThematic.getNomThematic(), thematicRequest.getNomThematic());
    }
}

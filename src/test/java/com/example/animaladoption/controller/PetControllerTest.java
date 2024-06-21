package com.example.animaladoption.controller;

import com.example.animaladoption.dto.request.PetRequestDto;
import com.example.animaladoption.dto.response.PetResponseDto;
import com.example.animaladoption.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getPetById() throws Exception {
        PetResponseDto petResponseDto = new PetResponseDto();
        petResponseDto.setName("Buddy");
        petResponseDto.setBreed("Dog");
        petResponseDto.setAge(2);
        petResponseDto.setSize(1);
        petResponseDto.setDescription("Friendly and playful");
        petResponseDto.setMedicalHistory("Vaccinated");
        petResponseDto.setBehavioralAssessment("Good with kids");
        petResponseDto.setStatus("Available");
        petResponseDto.setShelterName("Happy Tails Shelter");

        Mockito.when(petService.getPetById(1)).thenReturn(Optional.of(petResponseDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/pets/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(petResponseDto)));
    }

}
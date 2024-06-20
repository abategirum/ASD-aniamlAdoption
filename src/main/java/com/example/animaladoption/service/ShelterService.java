package com.example.animaladoption.service;

import com.example.animaladoption.dto.request.ShelterRequestDto;
import com.example.animaladoption.dto.response.ShelterResponseDto;
import com.example.animaladoption.model.Shelter;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    Optional<ShelterResponseDto> addShelter(ShelterRequestDto shelter);

    Optional<ShelterResponseDto> updateShelter(Integer shelterID, ShelterRequestDto shelter);

    Optional<ShelterResponseDto> getShelterById(Integer shelterId);

    List<ShelterResponseDto> getAllShelters();

    void deleteShelter(Integer shelterId);
}

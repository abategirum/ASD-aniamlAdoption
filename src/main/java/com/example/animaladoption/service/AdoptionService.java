package com.example.animaladoption.service;

import com.example.animaladoption.dto.request.AdoptionRequestDto;
import com.example.animaladoption.dto.response.AdoptionResponseDto;

import java.util.List;
import java.util.Optional;

public interface AdoptionService {
    Optional<AdoptionResponseDto> addAdoption(AdoptionRequestDto adoption);

    Optional<AdoptionResponseDto> updateAdoption(Integer adoptionId, AdoptionRequestDto adoption);

    Optional<AdoptionResponseDto> getAdoptionById(Integer id);

    List<AdoptionResponseDto> getAllAdoptions();

    void deleteAdoption(Integer id);
}

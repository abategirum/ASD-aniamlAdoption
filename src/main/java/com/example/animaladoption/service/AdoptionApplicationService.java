package com.example.animaladoption.service;

import com.example.animaladoption.dto.request.AdoptionApplicationRequestDto;
import com.example.animaladoption.dto.response.AdoptionApplicationResponseDto;
import com.example.animaladoption.model.AdoptionApplication;

import java.util.List;
import java.util.Optional;

public interface AdoptionApplicationService {
    Optional<AdoptionApplicationResponseDto> addAdoptionApplication(AdoptionApplicationRequestDto adoptionApplication);

    Optional<AdoptionApplicationResponseDto> updateAdoptionApplication(Integer applicationId, AdoptionApplicationRequestDto adoptionApplication);

    Optional<AdoptionApplicationResponseDto> getAdoptionApplicationById(Integer id);

    List<AdoptionApplicationResponseDto> getAllAdoptionApplications();

    void deleteAdoptionApplication(Integer id);
}

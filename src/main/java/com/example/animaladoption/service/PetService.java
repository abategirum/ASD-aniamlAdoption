package com.example.animaladoption.service;

import com.example.animaladoption.dto.request.PetRequestDto;
import com.example.animaladoption.dto.response.PetResponseDto;
import com.example.animaladoption.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Optional<PetResponseDto> addPet(PetRequestDto pet);

    Optional<PetResponseDto> updatePet(Integer petId, PetRequestDto pet);

    Optional<PetResponseDto> getPetById(Integer petId);

    List<PetResponseDto> getPets();

    void deletePet(Integer petId);
}

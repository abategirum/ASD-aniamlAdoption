package com.example.animaladoption.service.impl;

import com.example.animaladoption.dto.request.PetRequestDto;
import com.example.animaladoption.dto.response.PetResponseDto;
import com.example.animaladoption.model.Pet;
import com.example.animaladoption.model.Shelter;
import com.example.animaladoption.repository.PetRepository;
import com.example.animaladoption.repository.ShelterRepository;
import com.example.animaladoption.service.PetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<PetResponseDto> addPet(PetRequestDto pet) {
        try {
            Pet newPet = modelMapper.map(pet, Pet.class);
            Optional<Shelter> optionalShelter = shelterRepository.findById(pet.getShelterId());
            optionalShelter.ifPresent(newPet::setShelter);
            Pet savedPet = petRepository.save(newPet);
            PetResponseDto responseDto = modelMapper.map(savedPet, PetResponseDto.class);
            return Optional.of(responseDto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PetResponseDto> updatePet(Integer petId, PetRequestDto pet) {
        if (petRepository.existsById(petId)) {
            try {
                Pet updatedPet = modelMapper.map(pet, Pet.class);
                updatedPet.setPetId(petId);
                Pet savedPet = petRepository.save(updatedPet);
                PetResponseDto responseDto = modelMapper.map(savedPet, PetResponseDto.class);
                return Optional.of(responseDto);
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PetResponseDto> getPetById(Integer petId) {
        return petRepository.findById(petId)
                .map(pet -> modelMapper.map(pet, PetResponseDto.class));
    }

    @Override
    public List<PetResponseDto> getPets() {
        return petRepository.findAll().stream()
                .map(pet -> modelMapper.map(pet, PetResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePet(Integer petId) {
        petRepository.findById(petId).ifPresentOrElse(
                pet -> petRepository.deleteById(petId),
                () -> {
                    // Handle the case where the pet does not exist
                }
        );
    }
}
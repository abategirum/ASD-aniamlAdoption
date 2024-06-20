package com.example.animaladoption.service.impl;

import com.example.animaladoption.dto.request.ShelterRequestDto;
import com.example.animaladoption.dto.response.ShelterResponseDto;
import com.example.animaladoption.model.Shelter;
import com.example.animaladoption.repository.ShelterRepository;
import com.example.animaladoption.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ShelterResponseDto> addShelter(ShelterRequestDto shelter) {
        try {
            Shelter newShelter = modelMapper.map(shelter, Shelter.class);
            Shelter savedShelter = shelterRepository.save(newShelter);
            ShelterResponseDto responseDto = modelMapper.map(savedShelter, ShelterResponseDto.class);
            return Optional.of(responseDto);
        } catch (Exception e) {
            // Handle exception if any
            return Optional.empty();
        }
    }

    @Override
    public Optional<ShelterResponseDto> updateShelter(Integer shelterId, ShelterRequestDto shelter) {

        if (shelterRepository.existsById(shelterId)) {
            try {
                Shelter updatedShelter = modelMapper.map(shelter, Shelter.class);
                updatedShelter.setShelterId(shelterId);
                Shelter savedShelter = shelterRepository.save(updatedShelter);
                ShelterResponseDto responseDto = modelMapper.map(savedShelter, ShelterResponseDto.class);
                return Optional.of(responseDto);
            } catch (Exception e) {
                // Handle exception if any
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ShelterResponseDto> getShelterById(Integer shelterId) {
        return shelterRepository.findById(shelterId)
                .map(shelter -> modelMapper.map(shelter, ShelterResponseDto.class));
    }

    @Override
    public List<ShelterResponseDto> getAllShelters() {
        return shelterRepository.findAll().stream()
                .map(shelter -> modelMapper.map(shelter, ShelterResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteShelter(Integer shelterId) {
        shelterRepository.findById(shelterId).ifPresentOrElse(
                shelter -> shelterRepository.deleteById(shelterId),
                () -> {
                    // Handle the case where the shelter does not exist
                }
        );
    }
}

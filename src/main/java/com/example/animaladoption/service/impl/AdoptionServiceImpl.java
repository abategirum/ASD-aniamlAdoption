package com.example.animaladoption.service.impl;

import com.example.animaladoption.dto.request.AdoptionRequestDto;
import com.example.animaladoption.dto.response.AdoptionResponseDto;
import com.example.animaladoption.model.Adoption;
import com.example.animaladoption.repository.AdoptionRepository;
import com.example.animaladoption.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<AdoptionResponseDto> addAdoption(AdoptionRequestDto adoption) {
        try {
            Adoption newAdoption = modelMapper.map(adoption, Adoption.class);
            Adoption savedAdoption = adoptionRepository.save(newAdoption);
            AdoptionResponseDto responseDto = modelMapper.map(savedAdoption, AdoptionResponseDto.class);
            return Optional.of(responseDto);
        } catch (Exception e) {
            // Handle exception if any
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdoptionResponseDto> updateAdoption(Integer adoptionId, AdoptionRequestDto adoption) {
        if (adoptionRepository.existsById(adoptionId)) {
            try {
                Adoption updatedAdoption = modelMapper.map(adoption, Adoption.class);
                updatedAdoption.setAdoptionId(adoptionId);  // Ensure the ID is set for the existing adoption
                Adoption savedAdoption = adoptionRepository.save(updatedAdoption);
                AdoptionResponseDto responseDto = modelMapper.map(savedAdoption, AdoptionResponseDto.class);
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
    public Optional<AdoptionResponseDto> getAdoptionById(Integer id) {
        return adoptionRepository.findById(id)
                .map(adoption -> modelMapper.map(adoption, AdoptionResponseDto.class));
    }

    @Override
    public List<AdoptionResponseDto> getAllAdoptions() {
        return adoptionRepository.findAll().stream()
                .map(adoption -> modelMapper.map(adoption, AdoptionResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdoption(Integer id) {
        adoptionRepository.findById(id).ifPresentOrElse(
                adoption -> adoptionRepository.deleteById(id),
                () -> {
                    // Handle the case where the adoption does not exist
                }
        );
    }
}
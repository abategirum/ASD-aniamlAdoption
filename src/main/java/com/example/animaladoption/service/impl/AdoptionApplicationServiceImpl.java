package com.example.animaladoption.service.impl;

import com.example.animaladoption.dto.request.AdoptionApplicationRequestDto;
import com.example.animaladoption.dto.response.AdoptionApplicationResponseDto;
import com.example.animaladoption.model.AdoptionApplication;
import com.example.animaladoption.model.Pet;
import com.example.animaladoption.model.User;
import com.example.animaladoption.repository.AdoptionApplicationRepository;
import com.example.animaladoption.repository.PetRepository;
import com.example.animaladoption.repository.UserRepository;
import com.example.animaladoption.service.AdoptionApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdoptionApplicationServiceImpl implements AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    public Optional<AdoptionApplicationResponseDto> addAdoptionApplication(AdoptionApplicationRequestDto adoptionApplication) {
        try {
            AdoptionApplication newApplication = modelMapper.map(adoptionApplication, AdoptionApplication.class);
            Optional<User> optionalUser = userRepository.findById(adoptionApplication.getUserId());
            optionalUser.ifPresent(newApplication::setUser);
            Optional<Pet> optionalPet = petRepository.findById(adoptionApplication.getPetId());
            optionalPet.ifPresent(newApplication::setPet);
            AdoptionApplication savedApplication = adoptionApplicationRepository.save(newApplication);
            AdoptionApplicationResponseDto responseDto = modelMapper.map(savedApplication, AdoptionApplicationResponseDto.class);
            return Optional.of(responseDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdoptionApplicationResponseDto> updateAdoptionApplication(Integer applicationId, AdoptionApplicationRequestDto adoptionApplication) {
        if (adoptionApplicationRepository.existsById(applicationId)) {
            try {
                AdoptionApplication updatedApplication = modelMapper.map(adoptionApplication, AdoptionApplication.class);
                updatedApplication.setApplicationId(applicationId);
                AdoptionApplication savedApplication = adoptionApplicationRepository.save(updatedApplication);
                AdoptionApplicationResponseDto responseDto = modelMapper.map(savedApplication, AdoptionApplicationResponseDto.class);
                return Optional.of(responseDto);
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdoptionApplicationResponseDto> getAdoptionApplicationById(Integer id) {
        return adoptionApplicationRepository.findById(id)
                .map(application -> modelMapper.map(application, AdoptionApplicationResponseDto.class));
    }

    @Override
    public List<AdoptionApplicationResponseDto> getAllAdoptionApplications() {
        return adoptionApplicationRepository.findAll().stream()
                .map(application -> modelMapper.map(application, AdoptionApplicationResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdoptionApplication(Integer id) {
        adoptionApplicationRepository.findById(id).ifPresentOrElse(
                application -> adoptionApplicationRepository.deleteById(id),
                () -> {

                }
        );
    }
}
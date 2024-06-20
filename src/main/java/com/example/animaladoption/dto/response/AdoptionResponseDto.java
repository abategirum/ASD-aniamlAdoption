package com.example.animaladoption.dto.response;

import com.example.animaladoption.model.Pet;
import com.example.animaladoption.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionResponseDto {
    private UserResponseDto user;
    private PetResponseDto pet;
    private LocalDate adoptionDate;
}

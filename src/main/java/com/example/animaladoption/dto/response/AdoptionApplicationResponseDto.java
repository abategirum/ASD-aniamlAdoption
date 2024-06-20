package com.example.animaladoption.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionApplicationResponseDto {
    private UserResponseDto user;
    private PetResponseDto pet;
    private LocalDate applicationDate;
    private String status;
}

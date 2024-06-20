package com.example.animaladoption.dto.request;

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
public class AdoptionApplicationRequestDto {
    @NotBlank(message = "Blank-Null-Empty not allowed")
    private Integer userId;
    @NotBlank(message = "Blank-Null-Empty not allowed")
    private Integer petId;
    private LocalDate applicationDate;
}

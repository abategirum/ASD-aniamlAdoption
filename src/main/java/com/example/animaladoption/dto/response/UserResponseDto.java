package com.example.animaladoption.dto.response;

import com.example.animaladoption.model.Adoption;
import com.example.animaladoption.model.AdoptionApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String username;
    private String firstName;
    private String lastName;
}

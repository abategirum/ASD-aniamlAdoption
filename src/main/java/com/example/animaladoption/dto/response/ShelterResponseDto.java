package com.example.animaladoption.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelterResponseDto {
    private String name;
    private String address;
    private String contactInfo;
    private List<PetResponseDto> pets;
}

package com.example.animaladoption.dto.request;

import com.example.animaladoption.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelterRequestDto {
    private String name;
    private String address;
    private String contactInfo;
}

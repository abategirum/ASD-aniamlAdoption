package com.example.animaladoption.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRequestDto {
    private String name;
    private String breed;
    private int age;
    private int size;
    private String description;
    private String medicalHistory;
    private String behavioralAssessment;
    private String status;
    private Integer shelterId;
}

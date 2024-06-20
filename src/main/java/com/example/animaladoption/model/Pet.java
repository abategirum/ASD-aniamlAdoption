package com.example.animaladoption.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petId;
    private String name;
    private String breed;
    private int age;
    private int size;
    private String description;
    private String medicalHistory;
    private String behavioralAssessment;
    private String status;

    @ManyToOne
    @JoinColumn(name = "shelter_id", referencedColumnName = "shelterId")
    private Shelter shelter;

    @OneToOne(mappedBy = "pet")
    private Adoption adoptions;

    @OneToMany(mappedBy = "pet")
    private List<AdoptionApplication> adoptionApplications;

//    @OneToMany(mappedBy = "pet")
//    private List<Appointment> appointments;
}

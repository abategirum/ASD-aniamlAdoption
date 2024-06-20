package com.example.animaladoption.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "adoptions")
@Getter
@Setter
@NoArgsConstructor
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adoptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "petId", referencedColumnName = "petId")
    private Pet pet;

    private LocalDate adoptionDate;
}

package com.example.animaladoption.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "shelters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shelterId;
    private String name;
    private String address;
    private String contactInfo;

    @OneToMany(mappedBy = "shelter")
    private List<Pet> pets;
}

package com.example.animaladoption.repository;

import com.example.animaladoption.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Integer> {
}

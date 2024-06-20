package com.example.animaladoption.repository;

import com.example.animaladoption.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Integer> {
}

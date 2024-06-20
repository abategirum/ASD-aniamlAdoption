package com.example.animaladoption.repository;

import com.example.animaladoption.model.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Integer> {
}

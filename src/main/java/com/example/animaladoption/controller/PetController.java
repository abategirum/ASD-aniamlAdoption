package com.example.animaladoption.controller;


import com.example.animaladoption.dto.request.PetRequestDto;
import com.example.animaladoption.dto.response.PetResponseDto;
import com.example.animaladoption.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponseDto> addPet(@RequestBody PetRequestDto petRequestDto) {
        Optional<PetResponseDto> responseDto = petService.addPet(petRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDto> updatePet(@PathVariable Integer id, @RequestBody PetRequestDto petRequestDto) {
        Optional<PetResponseDto> responseDto = petService.updatePet(id, petRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> getPetById(@PathVariable Integer id) {
        Optional<PetResponseDto> responseDto = petService.getPetById(id);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDto>> getPets() {
        List<PetResponseDto> pets = petService.getPets();
        return ResponseEntity.ok(pets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}

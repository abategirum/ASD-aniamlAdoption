package com.example.animaladoption.controller;


import com.example.animaladoption.dto.request.ShelterRequestDto;
import com.example.animaladoption.dto.response.ShelterResponseDto;
import com.example.animaladoption.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shelters")
@RequiredArgsConstructor
public class ShelterController {
    private final ShelterService shelterService;

    @PostMapping
    public ResponseEntity<ShelterResponseDto> createShelter(@RequestBody ShelterRequestDto shelterRequestDto){
        Optional<ShelterResponseDto> responseDto = shelterService.addShelter(shelterRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShelterResponseDto> updateShelter(@PathVariable Integer id, @RequestBody ShelterRequestDto shelterRequestDto) {
        Optional<ShelterResponseDto> responseDto = shelterService.updateShelter(id, shelterRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterResponseDto> getShelterById(@PathVariable Integer id) {
        Optional<ShelterResponseDto> responseDto = shelterService.getShelterById(id);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ShelterResponseDto>> getAllShelters() {
        List<ShelterResponseDto> shelters = shelterService.getAllShelters();
        return ResponseEntity.ok(shelters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Integer id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.noContent().build();
    }

}

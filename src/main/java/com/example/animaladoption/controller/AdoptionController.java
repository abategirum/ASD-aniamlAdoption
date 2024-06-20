package com.example.animaladoption.controller;

import com.example.animaladoption.dto.request.AdoptionRequestDto;
import com.example.animaladoption.dto.response.AdoptionResponseDto;
import com.example.animaladoption.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<AdoptionResponseDto> addAdoption(@RequestBody AdoptionRequestDto adoptionRequestDto) {
        Optional<AdoptionResponseDto> responseDto = adoptionService.addAdoption(adoptionRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionResponseDto> updateAdoption(@PathVariable Integer id, @RequestBody AdoptionRequestDto adoptionRequestDto) {
        Optional<AdoptionResponseDto> responseDto = adoptionService.updateAdoption(id, adoptionRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionResponseDto> getAdoptionById(@PathVariable Integer id) {
        Optional<AdoptionResponseDto> responseDto = adoptionService.getAdoptionById(id);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AdoptionResponseDto>> getAllAdoptions() {
        List<AdoptionResponseDto> adoptions = adoptionService.getAllAdoptions();
        return ResponseEntity.ok(adoptions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoption(@PathVariable Integer id) {
        adoptionService.deleteAdoption(id);
        return ResponseEntity.noContent().build();
    }
}

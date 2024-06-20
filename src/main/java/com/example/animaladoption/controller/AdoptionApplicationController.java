package com.example.animaladoption.controller;

import com.example.animaladoption.dto.request.AdoptionApplicationRequestDto;
import com.example.animaladoption.dto.response.AdoptionApplicationResponseDto;
import com.example.animaladoption.service.AdoptionApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;

    @PostMapping
    public ResponseEntity<AdoptionApplicationResponseDto> addAdoptionApplication(@RequestBody AdoptionApplicationRequestDto adoptionApplicationRequestDto) {
        Optional<AdoptionApplicationResponseDto> responseDto = adoptionApplicationService.addAdoptionApplication(adoptionApplicationRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionApplicationResponseDto> updateAdoptionApplication(@PathVariable Integer id, @RequestBody AdoptionApplicationRequestDto adoptionApplicationRequestDto) {
        Optional<AdoptionApplicationResponseDto> responseDto = adoptionApplicationService.updateAdoptionApplication(id, adoptionApplicationRequestDto);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionApplicationResponseDto> getAdoptionApplicationById(@PathVariable Integer id) {
        Optional<AdoptionApplicationResponseDto> responseDto = adoptionApplicationService.getAdoptionApplicationById(id);
        return responseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AdoptionApplicationResponseDto>> getAllAdoptionApplications() {
        List<AdoptionApplicationResponseDto> applications = adoptionApplicationService.getAllAdoptionApplications();
        return ResponseEntity.ok(applications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoptionApplication(@PathVariable Integer id) {
        adoptionApplicationService.deleteAdoptionApplication(id);
        return ResponseEntity.noContent().build();
    }
}

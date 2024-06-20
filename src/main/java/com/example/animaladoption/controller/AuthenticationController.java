package com.example.animaladoption.controller;

import com.example.animaladoption.dto.request.AuthenticationRequestDTO;
import com.example.animaladoption.dto.request.RegisterRequestDTO;
import com.example.animaladoption.dto.response.AuthenticationResponseDTO;
import com.example.animaladoption.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        AuthenticationResponseDTO response = authenticationService.register(registerRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        AuthenticationResponseDTO response = authenticationService.authenticate(authenticationRequestDTO);
        return ResponseEntity.ok(response);
    }


}

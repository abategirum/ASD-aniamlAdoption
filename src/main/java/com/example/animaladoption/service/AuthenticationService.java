package com.example.animaladoption.service;

import com.example.animaladoption.dto.request.AuthenticationRequestDTO;
import com.example.animaladoption.dto.request.RegisterRequestDTO;
import com.example.animaladoption.dto.response.AuthenticationResponseDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}

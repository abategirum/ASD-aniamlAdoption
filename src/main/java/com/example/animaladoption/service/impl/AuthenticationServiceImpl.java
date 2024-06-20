package com.example.animaladoption.service.impl;

import com.example.animaladoption.dto.request.AuthenticationRequestDTO;
import com.example.animaladoption.dto.request.RegisterRequestDTO;
import com.example.animaladoption.dto.response.AuthenticationResponseDTO;
import com.example.animaladoption.model.User;
import com.example.animaladoption.repository.UserRepository;
import com.example.animaladoption.config.JwtService;
import com.example.animaladoption.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        // Create a user
        User user = new User(
                registerRequestDTO.getFirstName(),
                registerRequestDTO.getLastName(),
                registerRequestDTO.getUsername(),
                passwordEncoder.encode(registerRequestDTO.getPassword()),
                registerRequestDTO.getRole()
        );
        // Save user
        User savedUser = userRepository.save(user);
        //Generate a token
        String token = jwtService.generateToken(savedUser);

        return new AuthenticationResponseDTO(token);
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtService.generateToken(user);

        return new AuthenticationResponseDTO(token);
    }

}
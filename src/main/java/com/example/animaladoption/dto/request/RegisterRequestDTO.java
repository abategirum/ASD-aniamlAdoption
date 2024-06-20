package com.example.animaladoption.dto.request;

import com.example.animaladoption.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;
}

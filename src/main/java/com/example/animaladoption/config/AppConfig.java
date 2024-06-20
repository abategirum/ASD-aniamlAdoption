package com.example.animaladoption.config;


import com.example.animaladoption.dto.request.AdoptionApplicationRequestDto;
import com.example.animaladoption.dto.request.AdoptionRequestDto;
import com.example.animaladoption.dto.request.PetRequestDto;
import com.example.animaladoption.dto.response.*;
import com.example.animaladoption.model.*;
import com.example.animaladoption.repository.ShelterRepository;
import com.example.animaladoption.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.addMappings(new PropertyMap<AdoptionApplicationRequestDto, AdoptionApplication>() {
            @Override
            protected void configure() {
                // Explicitly map userId and petId to their respective properties
                map(source.getUserId(), destination.getUser().getUserId());
                map(source.getPetId(), destination.getPet().getPetId());
                skip(destination.getApplicationId()); // Skip mapping for applicationId
            }
        });
        mapper.addMappings(new PropertyMap<AdoptionApplication, AdoptionApplicationRequestDto>() {
            @Override
            protected void configure() {
                map(source.getUser().getUserId(), destination.getUserId());
                map(source.getPet().getPetId(), destination.getPetId());
                // Other properties mapping if necessary
            }
        });

        // AdoptionApplicationRequestDto to AdoptionApplication
//        mapper.addMappings(new PropertyMap<AdoptionApplicationRequestDto, AdoptionApplication>() {
//            @Override
//            protected void configure() {
//                map().getUser().setUserId(source.getUserId());
//                map().getPet().setPetId(source.getPetId());
//            }
//        });

        // AdoptionApplication to AdoptionApplicationRequestDto
//        mapper.addMappings(new PropertyMap<AdoptionApplication, AdoptionApplicationRequestDto>() {
//            @Override
//            protected void configure() {
//                map().setUserId(source.getUser().getUserId());
//                map().setPetId(source.getPet().getPetId());
//            }
//        });

        // AdoptionRequestDto to Adoption
//        mapper.addMappings(new PropertyMap<AdoptionRequestDto, Adoption>() {
//            @Override
//            protected void configure() {
//                map().getUser().setUserId(source.getUserId());
//                map().getPet().setPetId(source.getPetId());
//            }
//        });
//
//        // Adoption to AdoptionRequestDto
//        mapper.addMappings(new PropertyMap<Adoption, AdoptionRequestDto>() {
//            @Override
//            protected void configure() {
//                map().setUserId(source.getUser().getUserId());
//                map().setPetId(source.getPet().getPetId());
//            }
//        });
//
//        // AdoptionResponseDto to Adoption
//        mapper.addMappings(new PropertyMap<AdoptionResponseDto, Adoption>() {
//            @Override
//            protected void configure() {
//                map().setUser(mapper.map(source.getUser(), User.class));
//                map().setPet(mapper.map(source.getPet(), Pet.class));
//            }
//        });
//
//        // Adoption to AdoptionResponseDto
//        mapper.addMappings(new PropertyMap<Adoption, AdoptionResponseDto>() {
//            @Override
//            protected void configure() {
//                map().setUser(mapper.map(source.getUser(), UserResponseDto.class));
//                map().setPet(mapper.map(source.getPet(), PetResponseDto.class));
//            }
//        });
//
//        // AdoptionApplicationResponseDto to Adoption
//        mapper.addMappings(new PropertyMap<AdoptionApplicationResponseDto, Adoption>() {
//            @Override
//            protected void configure() {
//                map().setUser(mapper.map(source.getUser(), User.class));
//                map().setPet(mapper.map(source.getPet(), Pet.class));
//            }
//        });
//
//        // Adoption to AdoptionApplicationResponseDto
//        mapper.addMappings(new PropertyMap<Adoption, AdoptionApplicationResponseDto>() {
//            @Override
//            protected void configure() {
//                map().setUser(mapper.map(source.getUser(), UserResponseDto.class));
//                map().setPet(mapper.map(source.getPet(), PetResponseDto.class));
//            }
//        });

        // ShelterResponseDto to Adoption
//        mapper.addMappings(new PropertyMap<ShelterResponseDto, Shelter>() {
//            @Override
//            protected void configure() {
//                map().setPets(mapper.map(source.getPets(), new TypeToken<List<Pet>>(){}.getType()));
//            }
//        });
//
//        // Adoption to ShelterResponseDto
//        mapper.addMappings(new PropertyMap<Shelter, ShelterResponseDto>() {
//            @Override
//            protected void configure() {
//                map().setPets(mapper.map(source.getPets(), new TypeToken<List<PetResponseDto>>(){}.getType()));
//            }
//        });

        return mapper;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

}

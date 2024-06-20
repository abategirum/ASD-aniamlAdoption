package com.example.animaladoption.config;

import com.example.animaladoption.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //verify the resquest has Authorization header
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token = authHeader = authHeader.substring(7);
            Claims claims = jwtService.getClaimsFromToken(token);
            //from clains extract tge username
            String username = claims.getSubject();
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //set Authentication object in Security context(store username and authorities)
                var user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                               user,
                                null,
                                userDetailsService.loadUserByUsername(username).getAuthorities()
                        )
                );
                SecurityContextHolder.getContext()
                        .setAuthentication(
                                authenticationToken
                        );
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/api/v1/auth");
    }
}

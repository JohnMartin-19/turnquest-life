package com.turnquestlife.auth_service.service;

import com.turnquestlife.auth_service.dto.*;
import com.turnquestlife.auth_service.entity.*;
import com.turnquestlife.auth_service.repository.*;
import com.turnquestlife.auth_service.secuirty.*;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + request.getEmail());
        }

        User user = User.builder()
                .fullName(request.getfullName())
                .email(request.getEmail())
                .passowrd(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build()

        userRepository.save(user); //similar to .save() in Django
        log.info("New user registered: {}", user.getEmail());

        // generating a JWT token once the user has been registered successfully
        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();

    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        log.info("User logged in: {}", user.getEmail());

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .fullName(user.getFullName)
                .role(user.getRole)
                .build();
    }

}
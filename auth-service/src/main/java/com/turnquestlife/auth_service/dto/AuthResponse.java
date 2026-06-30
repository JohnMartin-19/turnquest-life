package com.turnquestlife.auth_service.dto;

import com.turnquest.auth_service.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
public class AuthResponse {

    private String fullName;
    private String email;
    private Role role;
    private String token;
}
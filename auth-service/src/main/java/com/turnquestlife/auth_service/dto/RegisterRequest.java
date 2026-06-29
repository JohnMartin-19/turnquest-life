package com.turnquestlife.dto;

import com.turnquest.auth_service.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
public class RegisterRequest {

    @NotBlank(message = "This field must be provided")
    private String fullName;

    @NotBlank("Email field is required")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank("Password is required")
    @Size(min = 6, message = "Password must be atleast 6 characters")
    private String password;

    @@NotNull(message = "Role is required")
    private Role role;
}
package com.turnquestlife.auth_servicedto;

import com.turnquest.auth_service.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
public class LoginRequest {

    @NotBlank(message = "Username must be provided")
    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank(message = "Password must be provided")
    @Size(min = 6, message = "Passowrd must be atleast 6 characters")
    private String password;

}
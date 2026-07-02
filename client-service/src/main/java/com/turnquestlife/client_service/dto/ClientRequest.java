package com.turnquestlife.client_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
public class ClientRequest {

    @NotBlank(message = "Name cannot be null")
    private String firstName;

    @NotBlank("Name cannot be blank")
    private String lastName;

    @NotBlank("Email must be provided")
    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank(message = "ID number ia required")
    private String idNumber;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender must be included")
    private Gender gender;

    private String address;
    private String occupation;

}
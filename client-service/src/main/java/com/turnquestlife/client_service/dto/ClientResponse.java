package com.turnquestlife.client_servive.dto;

import com.turnquestlife.client_service.entity.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String idNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private KycStatus kycStatus;
    private String address;
    private String occupation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
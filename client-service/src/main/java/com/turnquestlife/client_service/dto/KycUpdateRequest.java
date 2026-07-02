package com.turnquestlife.client_service.dto;

import com.turnquestlife.client_service.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class KycUpdateRequest {

    @NotNull(message = "KYC status is required")
    private KycStatus kycStatus;
}
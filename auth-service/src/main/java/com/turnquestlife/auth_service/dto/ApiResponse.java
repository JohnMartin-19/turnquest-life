package com.turnquestlife.auth_servicedto;

import com.turnquest.auth_service.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class ApiResponse {
    private String message;
    private boolean success;
    private <T> data;
}

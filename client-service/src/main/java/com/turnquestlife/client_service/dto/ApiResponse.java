package com.turnquestlife.client_service.dto;

import lombok.*;


@Data
@Builder
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T data;
}
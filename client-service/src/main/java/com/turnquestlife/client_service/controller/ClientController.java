package com.turnquestlife.client_service.controller;

import com.turnquestlife.client_service.dto.ApiResponse;
import com.turnquestlife.client_service.dto.ClientRequest;
import com.turnquestlife.client_service.dto.ClientResponse;
import com.turnquestlife.client_service.dto.KycUpdateRequest;
import com.turnquestlife.client_service.entity.KycStatus;
import com.turnquestlife.client_service.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Policy Holder Mngt Endpoints")

public class ClientController {

    private final clientService clientService;

    @Operation(summary = "Register a anew policy holder")
    @ApiResponse( value = {
            @ApiResponse(responseCode = "201", description = "Created a new policy holder successfully")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponse>> create(
            @Valid @RequestBody ClientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ClientResponse>builder()
                        .message("Client registered successfully")
                        .success(true)
                        .data(clientService.create(request))
                        .build());
    }

    @Operation(summary = "Get client by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<ClientResponse>builder()
                .message("Client retrieved successfully")
                .success(true)
                .data(clientService.getById(id))
                .build());
    }

    @Operation(summary = "Get client by national ID or passport number")
    @GetMapping("/by-id-number/{idNumber}")
    public ResponseEntity<ApiResponse<ClientResponse>> getByIdNumber(
            @PathVariable String idNumber) {
        return ResponseEntity.ok(ApiResponse.<ClientResponse>builder()
                .message("Client retrieved successfully")
                .success(true)
                .data(clientService.getByIdNumber(idNumber))
                .build());
    }

    @Operation(summary = "List all clients — paginated")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ClientResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortBy) {
        return ResponseEntity.ok(ApiResponse.<Page<ClientResponse>>builder()
                .message("Clients retrieved successfully")
                .success(true)
                .data(clientService.getAll(page, size, sortBy))
                .build());
    }

    @Operation(summary = "Search clients by name, email or ID number")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<ClientResponse>>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.<Page<ClientResponse>>builder()
                .message("Search completed")
                .success(true)
                .data(clientService.search(keyword, page, size))
                .build());
    }

    @Operation(summary = "Filter clients by KYC status")
    @GetMapping("/kyc-status/{status}")
    public ResponseEntity<ApiResponse<Page<ClientResponse>>> getByKycStatus(
            @PathVariable KycStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.<Page<ClientResponse>>builder()
                .message("Clients retrieved by KYC status")
                .success(true)
                .data(clientService.getByKycStatus(status, page, size))
                .build());
    }

    @Operation(summary = "Update client details")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(ApiResponse.<ClientResponse>builder()
                .message("Client updated successfully")
                .success(true)
                .data(clientService.update(id, request))
                .build());
    }

    @Operation(summary = "Update client KYC status")
    @PatchMapping("/{id}/kyc")
    public ResponseEntity<ApiResponse<ClientResponse>> updateKycStatus(
            @PathVariable Long id,
            @Valid @RequestBody KycUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.<ClientResponse>builder()
                .message("KYC status updated successfully")
                .success(true)
                .data(clientService.updateKycStatus(id, request))
                .build());
    }

    @Operation(summary = "Delete a client")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Client deleted successfully")
                .success(true)
                .build());
    }
}
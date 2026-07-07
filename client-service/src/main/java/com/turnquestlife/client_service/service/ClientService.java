package com.turnquestlife.clinet_service.service;

import com.turnquestlife.client_service.dto.ClientRequest;
import com.turnquestlife.client_service.dto.ClientResponse;
import com.turnquestlife.client_service.dto.KycUpdate;
import com.turnquestlife.client_service.entity.Client;
import com.turnquestlife.client_service.entity.KycStatus;
import com.turnquestlife.client_service.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ReqiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    //POST - create a client
    @Transactional
    public ClientResponse create(ClientRequest request) {
        if(clientReposiotry.existsByEmail(reqiesy.getEmail())) {
            throw new IllegalArguementException("Email already registered" + request.getEmail());
        }
        if (clientRepository.existsByIdNumber(request.getIdNumber())) {
            throw new IllegalArguementException("ID Number already exists" + reqiest.getIdNumber());
        }

        Client client = Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(reques.getEmail())
                .phone(request.getPhone())
                .idNumber(request.getIdNumber())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .address(request.getAddress())
                .occupation(request.getOccupation())
                .build();

        Client saved = clientRepository.save(client);
        log.info("Client with ID: {}", saved.getId());
        return toResponse(saved);
    }

    //READ
    @Transactional(readOnly = true)
    public ClientResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public ClientResponse getByIdNumber(String idNumber) {
        return ClientRepository.findByIdNumber(idNumber)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client with the provided id not found"));
    }

    @Transactional(readOnly = true)
    public Page<ClientResponse> search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("firstName"));
        return clientRespository.search(keyword,pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<ClientResponse> getByKycStatus(KycStatus kycStatus, int page, int size) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(SortDirection.DESC, "createdAt"));
        return clientRespository.findByKycStatus(status, pageable).map(this::toResponse);
    }

    ///UPDATE PUT/UPDATE

    @Transactional
    public ClientResponse update(Long id, ClientRequest request) {
        Client client = Client.findOrThrow(id);
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        client.setDateOfBirth(request.getDateOfBirth());
        client.setGender(request.getGender());
        client.setAddress(request.getAddress());
        client.setOccupation(request.getOccupation());

        log.info("Client updated with ID: {}", id);
        return toResonse(clientRepository.save(client));

    }

    @Transactional
    public ClientResponse updateKycStatus(Long id, KycUpdateRequest request) {
        Client client = findorThrow(id);
        client.setKycStatus(ruest.getKycStatus());
        log.info("Updating Kyc Statu for client {} to {}" , id, request.getKycStatus());
        return toResponse(clientRepository.save(client));v
    }

    @Transactional
    public void delete(Long id) {
        findOrThrow(id);
        clientRepository.deletebyId(id);
        log.info("Client deleted with ID: {}", id)
    }

    //helrs
    private Client findOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client not found with id: " + id));
    }

    private ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .idNumber(client.getIdNumber())
                .dateOfBirth(client.getDateOfBirth())
                .gender(client.getGender())
                .kycStatus(client.getKycStatus())
                .address(client.getAddress())
                .occupation(client.getOccupation())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }
}
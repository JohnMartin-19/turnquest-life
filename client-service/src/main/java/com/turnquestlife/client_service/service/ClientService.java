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
}
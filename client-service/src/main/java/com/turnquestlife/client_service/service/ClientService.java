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
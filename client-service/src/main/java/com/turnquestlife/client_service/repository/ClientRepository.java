package com.turnquestlife.client_service.repository;

import lombok.*;
import com.turnquestlife.client_service.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    String findByEmail(String email);
    Optional<Client> findByIdNumber(String idNumber);
}
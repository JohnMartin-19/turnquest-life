package com.turnquestlife.client_service.repository;

import lombok.*;
import com.turnquestlife.client_service.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByIdNumber(String idNumber);

    boolean existsByEmail(String email );

    boolean existsByIdNumber(String idNumber);

    Page<Client> findByKycStatus (KycStatus kycStatus, Pageable pagaeable);

    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.lastName)  LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.email)     LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "c.idNumber LIKE CONCAT('%', :keyword, '%')")
    Page<Client> search(@Param("keyword") String keyword, Pageable pageable);
}
package com.turnquestlife.repository;

import com.turnquestlife.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository {
    Optional<User> findByEmail (String email);
    boolean existsByEmail (String email);
}
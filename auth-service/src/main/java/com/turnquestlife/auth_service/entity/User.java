package com.turnquestlife.auth_service;

import jakarta.persistence.*;
import lombok.*;
import org.sringframework.data.annotation.CreateDate;
import org.sringframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(unique = true, length = 150, nullable = false)
    @Email
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    @Builder.Default
    private boolean active;

    @CreatedAt
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedAt
    @Column(updatable = true)
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword(){return password};

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonBlocked() {
        return true;
    }

    @Override
    public boolean isCredebtialsNonExpired() {
        return true;
    }

    @Override boolean isEnabled() {
        return active;
    }




}


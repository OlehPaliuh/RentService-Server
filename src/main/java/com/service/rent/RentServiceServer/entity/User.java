package com.service.rent.RentServiceServer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private boolean isAccountLocked;

    private boolean isAccountDisabled;

    @Column(name = "refresh_token_key", nullable = false)
    private String refreshTokenKey;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    public User() {
    }
}

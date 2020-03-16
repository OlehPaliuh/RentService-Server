package com.service.rent.RentServiceServer.entity;

import com.service.rent.RentServiceServer.entity.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @OneToMany(fetch = FetchType.LAZY)
    List<User> users;
}

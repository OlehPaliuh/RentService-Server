package com.service.rent.RentServiceServer.entity.dto;

import com.service.rent.RentServiceServer.entity.Apartment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccountDetailsDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String avatarPath;

    private String email;

    private String phoneNumber;

    private Double maklerProbabilityScore;

    private List<Apartment> ownApartmentList;

    private List<Apartment> favouriteList;

    private LocalDateTime lastLoginTime;

    private Boolean isOnline;
}

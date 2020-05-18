package com.service.rent.RentServiceServer.entity.dto;

import com.service.rent.RentServiceServer.entity.Apartment;
import lombok.Data;

import java.util.List;

@Data
public class AccountDetailsDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private List<Apartment> ownApartmentList;
}

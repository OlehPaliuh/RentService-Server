package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavouriteForAccountDto {
    private Long id;

    private ApartmentDto apartment;

    private Long accountId;

    private LocalDateTime dateTimeCreated;
}

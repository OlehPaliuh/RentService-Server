package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavouriteDto {
    private Long id;

    private Long apartmentId;

    private Long accountId;

    private LocalDateTime dateTimeCreated;
}

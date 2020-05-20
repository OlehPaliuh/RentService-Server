package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApartmentOverviewRequestDto {
    private Long accountId;

    private Long apartmentId;

    private String comment;

    private LocalDateTime dateTime;
}

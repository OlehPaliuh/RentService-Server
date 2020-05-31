package com.service.rent.RentServiceServer.entity.dto;

import com.service.rent.RentServiceServer.entity.enums.OverviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApartmentOverviewResponseDto {
    private Long id;

    private CommentAccountDto account;

    private Long apartmentId;

    private OverviewStatus status;

    private String requesterComment;

    private String ownerComment;

    private LocalDateTime dateTime;
}

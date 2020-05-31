package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;

    private LocalDateTime createdAt;

    private String content;

    private Long apartmentId;

    private CommentAccountDto owner;
}

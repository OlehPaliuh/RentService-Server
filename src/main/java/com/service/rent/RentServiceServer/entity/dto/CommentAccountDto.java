package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentAccountDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDateTime lastLoginTime;

}
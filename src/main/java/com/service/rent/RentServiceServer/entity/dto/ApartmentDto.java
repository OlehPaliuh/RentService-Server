package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentDto {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private Double area;

    private String tags;

    private Integer numberOfRooms;

    private LocationDto location;

    private Long accountId;

    private List<String> imageLinks;
}

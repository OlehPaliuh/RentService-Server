package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentDto {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private Double totalArea;

    private Double livingArea;

    private Integer floor;

    private boolean allowPets;

    private String buildingType;

    private String tags;

    private Integer numberOfRooms;

    private LocationDto location;

    private Long accountId;

    private List<String> imageLinks;

    private List<FavouriteDto> favouriteList;
}

package com.service.rent.RentServiceServer.entity.dto;

import com.service.rent.RentServiceServer.entity.Location;
import lombok.Data;

@Data
public class ApartmentDto {

    private String name1;

    private String description;

    private Double price;

    private Double area;

    private String address;

    private String tags;

    private Integer numberOfRooms;

    private Location location;

    private Long accountId;
}

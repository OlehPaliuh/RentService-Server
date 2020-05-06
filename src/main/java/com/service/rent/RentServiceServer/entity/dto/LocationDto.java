package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;

@Data
public class LocationDto {

    private String administrative_area_level_1;
    private String country;
    private String locality;
    private String political;
    private String postal_code;
    private String route;
    private String street_number;
    private String sublocality;
    private String fullAddress;
    private double latitude;
    private double longitude;
}

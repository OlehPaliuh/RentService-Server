package com.service.rent.RentServiceServer.controller.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CityApartmentDistributionDto {
    List<String> cities = new ArrayList<>();
    List<Long> apartmentCounts = new ArrayList<>();
}

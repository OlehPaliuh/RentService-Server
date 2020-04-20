package com.service.rent.RentServiceServer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Embeddable
@Data
public class ApartmentsSearchParameters {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> regions;

    private Integer minNumberOfRooms;
    private Integer maxNumberOfRooms;

    private Integer floorLow;
    private Integer floorHigh;

    private Integer priceLow;
    private Integer priceHigh;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tags;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> searchText;
}

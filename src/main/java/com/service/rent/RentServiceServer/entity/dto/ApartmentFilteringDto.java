package com.service.rent.RentServiceServer.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApartmentFilteringDto {

    private Integer roomsMin;
    private Integer roomsMax;

    private Integer floorMin;
    private Integer floorMax;

    private Integer priceMin;
    private Integer priceMax;

    private Integer livingAreaMin;
    private Integer livingAreaMax;

    private Integer totalAreaMin;
    private Integer totalAreaMax;

    private boolean newBuilding;
    private boolean oldBuilding;

    private boolean hasPhotos;
    private boolean allowPets;
    private String landlordUsername;

    private String fulltextSearchString;

    public boolean getAllowPets() {
        return allowPets;
    }

    public void setAllowPets(boolean allowPets) {
        this.allowPets = allowPets;
    }
}

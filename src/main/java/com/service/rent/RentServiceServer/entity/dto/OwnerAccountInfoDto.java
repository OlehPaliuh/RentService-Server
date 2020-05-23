package com.service.rent.RentServiceServer.entity.dto;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.ApartmentOverview;
import com.service.rent.RentServiceServer.entity.Favourite;
import lombok.Data;

import java.util.List;

@Data
public class OwnerAccountInfoDto {

    private boolean isLocked;

    private boolean isDisabled;

    private RoleDto role;

    private String firstName;

    private String lastName;

    private String avatarPath;

    private String lockReason;

    private String username;

    private String email;

    private String phoneNumber;

    private List<Apartment> ownApartmentList;

    private List<ApartmentOverview> apartmentOverviews;

    private List<FavouriteForAccountDto> favouriteList;
}

package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Location;
import com.service.rent.RentServiceServer.entity.dto.LocationDto;
import com.service.rent.RentServiceServer.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public Location createLocation(LocationDto newLocation) {
        Location location = null;
        if (newLocation != null) {
            location = new Location();
            location.setLatitude(newLocation.getLatitude());
            location.setLongitude(newLocation.getLongitude());
            location.setFullAddress(newLocation.getFullAddress().replace("'", ""));
            location.setCity(newLocation.getLocality().replace("'", ""));
            location.setSublocality(newLocation.getSublocality());
            location.setCountry(newLocation.getCountry().replace("'", ""));
            location.setAdministrativeArea(newLocation.getAdministrative_area_level_1());
            location.setPolitical(newLocation.getPolitical());
            location.setRoute(newLocation.getRoute());
            location.setStreetNumber(newLocation.getStreet_number());
            location.setPostalCode(newLocation.getPostal_code());
            locationRepo.save(location);
        }
        return location;
    }
}

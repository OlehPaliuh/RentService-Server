package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.Location;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.entity.dto.ApartmentFilteringDto;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import com.service.rent.RentServiceServer.entity.enums.BuildingType;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepo apartmentRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private  LocationService locationService;

    @Autowired
    private ImageService imageService;

    public Iterable<Apartment> getAll() {
        return apartmentRepo.findAll();
    }

    public Apartment getApartmentById(Long id) {
        return apartmentRepo.findById(id).get();
    }

    public Apartment createApartment(ApartmentDto newApartment) {
        Apartment apartment = new Apartment();
        apartment.setTitle(newApartment.getTitle());
        apartment.setDescription(newApartment.getDescription());
        apartment.setNumberOfRooms(newApartment.getNumberOfRooms());
        apartment.setPrice(newApartment.getPrice());
        apartment.setTotalArea(newApartment.getTotalArea());
        apartment.setTags(newApartment.getTags());
        apartment.setStatus(ApartmentStatus.CREATED);
        apartment.setImageLinks(newApartment.getImageLinks());
        apartment.setLivingArea(newApartment.getLivingArea());
        apartment.setFloor(newApartment.getFloor());
        apartment.setAllowPets(newApartment.isAllowPets());
        apartment.setBuildingType("New building".equals(newApartment.getBuildingType().trim()) ? BuildingType.NEW_BUILDING : BuildingType.OLD_BUILDING);
        if(newApartment.getLocation() != null) {
           Location location = locationService.createLocation(newApartment.getLocation());
           apartment.setLocation(location);
        }
        apartment.setOwner(accountService.getById(newApartment.getAccountId()));
        return saveApartment(apartment);
    }

    public Apartment saveApartment(Apartment apartment) {
        return apartmentRepo.save(apartment);
    }

    public List<Apartment> getFilteredApartments(ApartmentFilteringDto apartmentFilter) {

        return apartmentRepo.findAll().stream()
                .filter(a -> !apartmentFilter.isHasPhotos() || a.getImageLinks().size() > 0)
                .filter(a -> !apartmentFilter.getAllowPets() || a.isAllowPets())
                .filter(a -> !apartmentFilter.isNewBuilding() || BuildingType.NEW_BUILDING.equals(a.getBuildingType()))
                .filter(a -> !apartmentFilter.isOldBuilding() || BuildingType.OLD_BUILDING.equals(a.getBuildingType()))
                .filter(a -> apartmentFilter.getPriceMin() == null || apartmentFilter.getPriceMin() <= a.getPrice())
                .filter(a -> apartmentFilter.getPriceMax() == null || apartmentFilter.getPriceMax() >= a.getPrice())
                .filter(a -> apartmentFilter.getFloorMin() == null || apartmentFilter.getFloorMin() <= a.getFloor())
                .filter(a -> apartmentFilter.getFloorMax() == null || apartmentFilter.getFloorMax() >= a.getFloor())
                .filter(a -> apartmentFilter.getLivingAreaMin() == null || apartmentFilter.getLivingAreaMin() <= a.getLivingArea())
                .filter(a -> apartmentFilter.getLivingAreaMax() == null || apartmentFilter.getLivingAreaMax() >= a.getLivingArea())
                .filter(a -> apartmentFilter.getRoomsMin() == null || apartmentFilter.getRoomsMin() <= a.getNumberOfRooms())
                .filter(a -> apartmentFilter.getRoomsMax() == null || apartmentFilter.getRoomsMax() >= a.getNumberOfRooms())
                .filter(a -> apartmentFilter.getTotalAreaMin() == null || apartmentFilter.getTotalAreaMin() <= a.getTotalArea())
                .filter(a -> apartmentFilter.getTotalAreaMax() == null || apartmentFilter.getTotalAreaMax() >= a.getTotalArea())
                .collect(Collectors.toList());
    }
}
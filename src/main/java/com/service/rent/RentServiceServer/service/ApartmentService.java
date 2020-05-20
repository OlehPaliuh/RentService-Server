package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.Location;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        apartment.setArea(newApartment.getArea());
        apartment.setTags(newApartment.getTags());
        apartment.setStatus(ApartmentStatus.CREATED);
        apartment.setImageLinks(newApartment.getImageLinks());
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
}

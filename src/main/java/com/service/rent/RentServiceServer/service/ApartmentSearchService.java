package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.repository.impl.ApartmentSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentSearchService {

    @Autowired
    private ApartmentSearchRepo apartmentSearchRepo;

    public List<Apartment> searchApartments(String searchText) {
        return apartmentSearchRepo.searchApartmentsQuery(searchText);
    }
}

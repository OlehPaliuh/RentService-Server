package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public @ResponseBody
    Iterable<Apartment> getAllApartments() {
        return apartmentService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Apartment getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id);
    }

    @PostMapping(path = "/create")
    public @ResponseBody
    Apartment createApartment(@RequestBody ApartmentDto apartment) {
        return apartmentService.createApartment(apartment);
    }
}

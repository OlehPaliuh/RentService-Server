package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.service.ApartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public @ResponseBody
    List<ApartmentDto> getAllApartments() {
        List<Apartment> apartmentList = new ArrayList<>();
        apartmentService.getAll().iterator().forEachRemaining(apartmentList::add);
        return apartmentList.stream().map(obj -> modelMapper.map(obj, ApartmentDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ApartmentDto getApartmentById(@PathVariable Long id) {
        Apartment apartment = apartmentService.getApartmentById(id);
        ApartmentDto apartmentDto = modelMapper.map(apartment, ApartmentDto.class);
        apartmentDto.setAccountId(apartment.getOwner().getId());
        return apartmentDto;
    }

    @PostMapping(path = "/create")
    public @ResponseBody
    Apartment createApartment(@RequestBody ApartmentDto apartment) {
        return apartmentService.createApartment(apartment);
    }
}

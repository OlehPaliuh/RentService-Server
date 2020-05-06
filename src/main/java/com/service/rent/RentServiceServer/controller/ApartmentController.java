package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.service.ApartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
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
        List<Apartment> apartmentList =new ArrayList<>();
        apartmentService.getAll().iterator().forEachRemaining(apartmentList::add);
        return apartmentList.stream().map(obj -> modelMapper.map(obj, ApartmentDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ApartmentDto getApartmentById(@PathVariable Long id) {
        return modelMapper.map(apartmentService.getApartmentById(id), ApartmentDto.class);
    }

    @PostMapping(path = "/create")
    public @ResponseBody
    Apartment createApartment(@RequestBody ApartmentDto apartment) {
        return apartmentService.createApartment(apartment);
    }
}

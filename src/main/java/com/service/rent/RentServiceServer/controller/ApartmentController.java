package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.dto.AccountDetailsDto;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.entity.dto.ApartmentFilteringDto;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import com.service.rent.RentServiceServer.entity.enums.SortingType;
import com.service.rent.RentServiceServer.service.ApartmentService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    List<ApartmentDto> getAllApartments(@PathParam("sortBy") SortingType sortBy) {
        List<Apartment> apartmentList = new ArrayList<>();
        apartmentService.getAll(sortBy).iterator().forEachRemaining(apartmentList::add);
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

    @PostMapping(path = "/filtering", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ApartmentDto> getFilteredApartments(@PathParam("sortBy")SortingType sortBy, @RequestBody ApartmentFilteringDto apartmentFilter) {
        return apartmentService.getFilteredApartments(apartmentFilter, sortBy).stream()
                .map(obj -> modelMapper.map((obj), ApartmentDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/{apartmentId}/status/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ApartmentDto apartmentStatusUpdate(@PathVariable Long apartmentId, @PathParam("status") ApartmentStatus status) throws NotFoundException {
        return modelMapper.map((apartmentService.updateStatus(apartmentId, status)), ApartmentDto.class);
    }

    @PostMapping(path = "/{apartmentId}/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    boolean apartmentStatusUpdate(@PathVariable Long apartmentId,  @RequestBody AccountDetailsDto account) throws Exception {
        return apartmentService.deleteApartment(apartmentId, account);
    }

}

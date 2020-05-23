package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.service.ApartmentSearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/")
public class ApartmentSearchController {

    @Autowired
    private ApartmentSearchService apartmentSearchService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "search")
    public @ResponseBody
    List<ApartmentDto> getAllApartments(@PathParam("q") String q) {
        return apartmentSearchService.searchApartments(q).stream()
                .map(obj -> modelMapper.map(obj, ApartmentDto.class))
                .collect(Collectors.toList());
    }

}

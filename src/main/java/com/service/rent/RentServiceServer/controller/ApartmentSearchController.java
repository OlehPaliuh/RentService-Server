package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.service.ApartmentSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class ApartmentSearchController {

    @Autowired
    private ApartmentSearchService apartmentSearchService;

    @GetMapping(path = "search")
    public @ResponseBody
    List<Apartment> getAllApartments(@PathParam("q") String q) {
        return apartmentSearchService.searchApartments(q);
    }

}

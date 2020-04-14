package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartments;
import com.service.rent.RentServiceServer.service.RealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/realty")
public class RealtyController {

    @Autowired
    private RealtyService realtyService;

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public @ResponseBody
    Iterable<Apartments> getAllRealty() {
        return realtyService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Apartments getRealtyById(@PathVariable Long id) {
        return realtyService.getRealtyById(id);
    }
}

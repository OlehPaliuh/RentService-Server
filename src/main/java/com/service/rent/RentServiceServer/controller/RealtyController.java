package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Realty;
import com.service.rent.RentServiceServer.service.RealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/realty")
public class RealtyController {

    @Autowired
    private RealtyService realtyService;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Realty> getAllRealty() {
        return realtyService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Realty getRealtyById(@PathVariable Long id) {
        return realtyService.getRealtyById(id);
    }
}

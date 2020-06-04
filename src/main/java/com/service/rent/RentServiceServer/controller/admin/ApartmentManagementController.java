package com.service.rent.RentServiceServer.controller.admin;

import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.service.ApartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin/apartment")
public class ApartmentManagementController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/lock/{id}")
    public ApartmentDto toggleLock(@PathVariable Long id) {
        return modelMapper.map(apartmentService.toggleLock(id), ApartmentDto.class);
    }
}

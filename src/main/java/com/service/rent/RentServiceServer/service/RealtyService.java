package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Apartments;
import com.service.rent.RentServiceServer.repository.RealtyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealtyService {

    @Autowired
    private RealtyRepo realtyRepo;

    public Iterable<Apartments> getAll() {
        return realtyRepo.findAll();
    }

    public Apartments getRealtyById(Long id) {
        return realtyRepo.findById(id).get();
    }
}

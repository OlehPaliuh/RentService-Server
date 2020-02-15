package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Realty;
import com.service.rent.RentServiceServer.repository.RealtyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealtyService {

    @Autowired
    private RealtyRepo realtyRepo;

    public Iterable<Realty> getAll() {
        return realtyRepo.findAll();
    }

    public Realty getRealtyById(Long id) {
        return realtyRepo.findById(id).get();
    }
}

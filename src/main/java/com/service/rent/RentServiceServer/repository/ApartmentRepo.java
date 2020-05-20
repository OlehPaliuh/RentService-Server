package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
    Apartment getApartmentById(Long apartmentId);
}

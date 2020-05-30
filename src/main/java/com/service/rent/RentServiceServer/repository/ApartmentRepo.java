package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
    Apartment getApartmentById(Long apartmentId);

    Long countApartmentsByCreateDateAfter(LocalDateTime date);
}

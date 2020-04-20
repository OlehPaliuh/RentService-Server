package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepo extends CrudRepository<Apartment, Long> {
}

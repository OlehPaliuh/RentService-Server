package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Apartments;
import org.springframework.data.repository.CrudRepository;

public interface RealtyRepo extends CrudRepository<Apartments, Long> {
}

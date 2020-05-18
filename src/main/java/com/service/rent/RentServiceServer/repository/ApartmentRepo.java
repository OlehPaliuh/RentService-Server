package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Apartment;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface ApartmentRepo extends CrudRepository<Apartment, Long> {

}

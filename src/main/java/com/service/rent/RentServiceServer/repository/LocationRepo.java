package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends CrudRepository<Location, Long> {
}

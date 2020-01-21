package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}

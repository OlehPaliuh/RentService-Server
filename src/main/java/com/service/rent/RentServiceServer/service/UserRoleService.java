package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.UserRole;
import com.service.rent.RentServiceServer.repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    public UserRole getUserRoleById(Long id) {
        return userRoleRepo.findById(id).orElse(null);
    }
}

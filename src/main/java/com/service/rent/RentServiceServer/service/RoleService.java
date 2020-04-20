package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Role;
import com.service.rent.RentServiceServer.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role getRoleById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }
}

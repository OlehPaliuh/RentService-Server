package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Role;
import com.service.rent.RentServiceServer.entity.enums.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    Role getRoleByName(RoleName name);
}

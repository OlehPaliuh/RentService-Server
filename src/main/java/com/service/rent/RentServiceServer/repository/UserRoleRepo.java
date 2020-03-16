package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.UserRole;
import com.service.rent.RentServiceServer.entity.enums.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Long> {

    UserRole getUserRoleByName(RoleName name);
}

package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    Optional<User> getUserByUsernameAndEmail(String username, String email);

    @Modifying
    @Query(value = "UPDATE User SET refreshTokenKey=:refreshTokenKey WHERE id=:id")
    int updateUserRefreshToken(String refreshTokenKey, Long id);
}

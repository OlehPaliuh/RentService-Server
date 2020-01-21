package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
       return userRepo.save(user);
    }
}

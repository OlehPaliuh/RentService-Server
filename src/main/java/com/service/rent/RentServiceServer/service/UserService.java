package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepo.delete(getById(id));
    }
}

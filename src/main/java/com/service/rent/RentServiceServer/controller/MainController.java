package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public User saveUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user1 = new User();
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        return userService.saveUser(user1);
    }

    @PutMapping(path = "/update/{id}")
    public User saveUser(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName) {
        User user1 = userService.getById(id);
        if (user1 != null) {
            user1.setFirstName(firstName);
            user1.setLastName(lastName);
            return userService.saveUser(user1);
        }
        return null;
    }

    @DeleteMapping(path = "/delete/{id}")
    public void saveUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

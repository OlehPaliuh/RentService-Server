package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public Iterable<User> getStartPage() {
        return userService.getUsers();
    }

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

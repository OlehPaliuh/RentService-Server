package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getStartPage() {
        return userService.getUsers();
    }

    @PostMapping(path = "/save")
    public @ResponseBody User saveUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user1 = new User();
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        return userService.saveUser(user1);
    }
}

package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private AccountService accountService;

    @PutMapping(path = "/update/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName) {
        Account updateAccount = accountService.getById(id);
        if (updateAccount != null) {
            updateAccount.setFirstName(firstName);
            updateAccount.setLastName(lastName);
            return accountService.saveAccount(updateAccount);
        }
        return null;
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}

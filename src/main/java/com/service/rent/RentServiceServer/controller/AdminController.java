package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/all_users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<Account> getAllAccounts() {
        return accountService.getAccounts();
    }
}

package com.service.rent.RentServiceServer.security.controller;

import com.service.rent.RentServiceServer.security.dto.JwtRequestDto;
import com.service.rent.RentServiceServer.security.dto.RegisterAccountDto;
import com.service.rent.RentServiceServer.security.service.SecurityServiceImpl;
import com.service.rent.RentServiceServer.security.service.AuthenticationService;
import com.service.rent.RentServiceServer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(path = "/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityServiceImpl securityService;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> registration(@RequestBody RegisterAccountDto account) throws Exception {
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> authentication(@RequestBody JwtRequestDto authenticationRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticateUser(authenticationRequest));
    }

    @GetMapping(path = "/updateAccessToken")
    public ResponseEntity<Object> updateAccessToken(@RequestParam @NotBlank String refreshToken) throws Exception {
        return ResponseEntity.ok().body(securityService.updateAccessTokens(refreshToken));
    }
}

package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.dto.AccountDetailsDto;
import com.service.rent.RentServiceServer.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "{accountId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public @ResponseBody
    AccountDetailsDto getAccountById(@PathVariable Long accountId) {
        return modelMapper.map(accountService.getById(accountId), AccountDetailsDto.class);
    }
}

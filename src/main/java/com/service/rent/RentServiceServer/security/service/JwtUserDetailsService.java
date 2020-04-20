package com.service.rent.RentServiceServer.security.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.exception.UserNotFoundException;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.security.dto.JwtUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        Account account = accountRepo.getAccountByUsername(username).orElse(null);
        if (account == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(account.getRole().getName().toString()));

        return new JwtUserDto(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                list,
                false
        );
    }
}

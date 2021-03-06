package com.service.rent.RentServiceServer.security.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.exception.IncorrectCredentialsException;
import com.service.rent.RentServiceServer.exception.UserDisabledException;
import com.service.rent.RentServiceServer.security.dto.JwtRequestDto;
import com.service.rent.RentServiceServer.security.dto.JwtResponseDto;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import com.service.rent.RentServiceServer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;

@Service
public class AuthenticationService {

    @Autowired
    private AccountService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponseDto authenticateUser(JwtRequestDto request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        Account userDetails = userDetailsService.getByUsername(request.getUsername());
        if (userDetails.isDisabled()) {
            throw new UserDisabledException(" Mail has already sent. Confirm account.");
        }
        if (userDetails.isLocked()) {
            throw new AccountLockedException("This account is locked, contact administrator.");
        }
        return new JwtResponseDto(jwtTokenUtil.generateAccessToken(userDetails), jwtTokenUtil.generateRefreshToken(userDetails),
                                  userDetails.getId(), userDetails.getUsername());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new IncorrectCredentialsException("Incorrect username of password", e);
        }
    }
}

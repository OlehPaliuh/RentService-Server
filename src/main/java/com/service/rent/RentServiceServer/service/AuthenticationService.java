package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.security.JwtRequest;
import com.service.rent.RentServiceServer.security.JwtResponse;
import com.service.rent.RentServiceServer.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse authenticateUser(JwtRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final User userDetails = userDetailsService.getByUsername(request.getUsername());
        return new JwtResponse(jwtTokenUtil.generateAccessToken(userDetails), jwtTokenUtil.generateRefreshToken(userDetails));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

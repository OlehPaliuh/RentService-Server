package com.service.rent.RentServiceServer.security.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.security.dto.JwtResponseDto;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import com.service.rent.RentServiceServer.service.AccountService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityServiceImpl {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountService accountService;

    @Transactional
    public JwtResponseDto updateAccessTokens(String refreshToken)throws Exception{
        String username;
        try {
            username = jwtTokenUtil.getUsernameFromRefreshToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new Exception("REFRESH_TOKEN_NOT_VALID");
        }
        Account account = accountService
                .getByUsername(username);
//              .orElseThrow(() -> new BadEmailException(USER_NOT_FOUND_BY_EMAIL + email));

        if(account == null) {
            throw new Exception("User not found by username");
        }
//        checkUserStatus(user);
        String newRefreshTokenKey = jwtTokenUtil.generateTokenKey();
        accountService.updateAccountRefreshToken(newRefreshTokenKey, account.getId());
        if (jwtTokenUtil.isTokenValid(refreshToken, account.getRefreshTokenKey())) {
            account.setRefreshTokenKey(newRefreshTokenKey);
            return new JwtResponseDto(
                    jwtTokenUtil.generateAccessToken(account),
                    jwtTokenUtil.generateRefreshToken(account),
                    account.getId()
            );
        }
        throw new Exception("REFRESH_TOKEN_NOT_VALID");
    }

//    private void checkUserStatus(User user) {
//        UserStatus status = user.getUserStatus();
//        if (status == UserStatus.BLOCKED) {
//            throw new UserBlockedException(USER_DEACTIVATED);
//        } else if (status == UserStatus.DEACTIVATED) {
//            throw new UserDeactivatedException(USER_DEACTIVATED);
//        }
//    }
}

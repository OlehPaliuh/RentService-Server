package com.service.rent.RentServiceServer.security.service;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.security.dto.JwtResponseDto;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import com.service.rent.RentServiceServer.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityServiceImpl {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Transactional
    public JwtResponseDto updateAccessTokens(String refreshToken)throws Exception{
        String username;
        try {
            username = jwtTokenUtil.getUsernameFromRefreshToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new Exception("REFRESH_TOKEN_NOT_VALID");
        }
        User user = userService
                .getByUsername(username);
//              .orElseThrow(() -> new BadEmailException(USER_NOT_FOUND_BY_EMAIL + email));

        if(user == null) {
            throw new Exception("User not found by username");
        }
//        checkUserStatus(user);
        String newRefreshTokenKey = jwtTokenUtil.generateTokenKey();
        userService.updateUserRefreshToken(newRefreshTokenKey, user.getId());
        if (jwtTokenUtil.isTokenValid(refreshToken, user.getRefreshTokenKey())) {
            user.setRefreshTokenKey(newRefreshTokenKey);
            return new JwtResponseDto(
                    jwtTokenUtil.generateAccessToken(user),
                    jwtTokenUtil.generateRefreshToken(user)
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

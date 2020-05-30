package com.service.rent.RentServiceServer.security.dto;

import java.io.Serializable;

public class JwtResponseDto implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final Long id;
    private final String username;
    private final String accessToken;
    private final String refreshToken;

    public JwtResponseDto(String accessToken, String refreshToken, Long id, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

package com.rahimov.lawfirmmanagementdeploy.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public interface JWTService {


    String generateToken(UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}

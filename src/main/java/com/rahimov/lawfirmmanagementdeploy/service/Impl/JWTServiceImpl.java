package com.rahimov.lawfirmmanagementdeploy.service.Impl;

import com.rahimov.lawfirmmanagementdeploy.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${secret.key.value}")
    private static String SECRET_KEY;
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000 * 300))
                .signWith(getSignature(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extractClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000 * 900))
                .signWith(getSignature(),SignatureAlgorithm.HS256)
                .compact();

    }
    private Key getSignature() {
        if (SECRET_KEY==null){
            byte[] keyBytes = new byte[32];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(keyBytes);

            SECRET_KEY = Base64.getEncoder().encodeToString(keyBytes);
            System.out.println("Generated Key: " + SECRET_KEY);
        }
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

//    private String encoder(){
//        byte[]secret_key = SECRET_KEY.getBytes();
//       return Base64.getEncoder().encodeToString(secret_key);
//    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){

         final Claims claims = extractAllClaims(token);
           return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignature())
                .build().parseSignedClaims(token)
                .getPayload();
    }


    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){

        String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }



}
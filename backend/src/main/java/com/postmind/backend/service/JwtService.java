package com.postmind.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


// jwt creation of token
// extract email
// token valid
@Service
public class JwtService {
     @Value("${app.jwt.secret}")
     private String jwtSecret;

     @Value("${app.jwt.expiration}")
    private long jwtExpiration;

     private SecretKey secretKey;

//     application starts => Secret Key
    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

//   jwt token
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(secretKey)
                .compact();
    }

    // extract email
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    //validate Token
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);

        return extractedEmail.equals(email) && !isTokenExpired(token);
    }

    // Generic method
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

// check expiration
    public boolean isTokenExpired(String token) {
        return extractClaim(
                token, Claims::getExpiration
        ).before(new Date());
    }



}

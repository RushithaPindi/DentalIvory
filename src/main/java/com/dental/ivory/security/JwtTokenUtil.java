package com.dental.ivory.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    // Secret key used for signing the token
    private long expirationTime = 3600000;  // Token expiration time in milliseconds (1 hour)
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Set the subject (usually username/email)
                .setIssuedAt(new Date())  // Set the token issue time
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // Set the expiration time
                .signWith(SignatureAlgorithm.HS512, secretKey)  // Sign the token with the secret key
                .compact();  // Compact the token into a string
    }

    // Validate JWT token
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Extract username from token
//    public String extractUsername(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Check if the token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from token
//    public Date extractExpiration(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();
//    }

    public Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}

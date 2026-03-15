package com.example.powerseva1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 🔐 Secret key (minimum 32 characters)
    private static final String SECRET =
            "powerseva_secret_key_1234567890123456";

    private static final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Generate Token
    public static String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                ) // 24 hours
                .signWith(key)
                .compact();
    }

    // ✅ Extract Claims
    public static Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
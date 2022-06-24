package com.example.demo.util;

import com.example.demo.config.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private final static String SECRET_KEY = "secret";

    public static String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstname", ((UserDetailsImpl)userDetails).getFirstname());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public static Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public static String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }
}

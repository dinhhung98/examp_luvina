package com.example.config;

import com.example.model.User;
import com.example.util.Constance;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtConfig {

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("username", user.getUsername())
                .claim("role", "user") // Lưu trữ thông tin role trong token.
                .setSubject(user.getPassword())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .signWith(Constance.key)
                .compact();
    }
}

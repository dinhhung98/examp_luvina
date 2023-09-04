package com.example.controller;

import com.example.config.JwtConfig;
import com.example.model.User;
import com.example.util.Constance;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examp")
public class AuthController {
    @Autowired
    JwtConfig jwtConfig;
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return jwtConfig.generateToken(user);
    }
    @PostMapping("/getInfo")
    public String getInfo(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("data: " + authorizationHeader);
        String token = authorizationHeader.substring(7); // Bỏ "Bearer " từ header.
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Constance.key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String subject = claims.getSubject();
            String username = (String)claims.get("username");
            String role = (String) claims.get("role"); // Lấy thông tin role từ token.
            return "username: " + username + " role: " + role + " subject: " + subject;
        } catch (Exception e) {
            return "Unauthorized";
        }
    }
}

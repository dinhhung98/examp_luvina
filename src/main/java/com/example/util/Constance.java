package com.example.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class Constance {
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}

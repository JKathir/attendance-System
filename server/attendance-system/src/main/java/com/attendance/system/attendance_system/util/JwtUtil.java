package com.attendance.system.attendance_system.util;

import com.attendance.system.attendance_system.config.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    private final String SECRET_KEY = "secret";

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_name", username);

        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .addClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

}

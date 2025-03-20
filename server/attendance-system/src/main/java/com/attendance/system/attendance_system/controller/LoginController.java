package com.attendance.system.attendance_system.controller;

import com.attendance.system.attendance_system.config.Constants;
import com.attendance.system.attendance_system.dto.AuthResponse;
import com.attendance.system.attendance_system.dto.LoginRequest;
import com.attendance.system.attendance_system.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (Constants.ADMIN.equals(request.getUsername()) && Constants.PASSWORD.equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        //If the username and password wrong send unauthorized msg
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constants.INVALID_CREDENTIALS);
    }
}


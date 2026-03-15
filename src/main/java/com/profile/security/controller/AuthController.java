package com.profile.security.controller;

import com.profile.common.PathConstant;
import com.profile.security.dto.request.LoginRequest;
import com.profile.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static com.profile.common.PathConstant.LOGIN;

@CrossOrigin
@RestController
@RequestMapping(PathConstant.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.authenticate(request);
//        return ResponseEntity.ok(new JwtResponse(token));
        return ResponseEntity.status(HttpStatus.OK)
                .header("token", token)
                .header("Access-Control-Expose-Headers", "token")
                .body(Map.of("message", "Authenticated Success", "timestamp", LocalDateTime.now()));
    }
}

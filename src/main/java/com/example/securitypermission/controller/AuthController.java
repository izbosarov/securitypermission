package com.example.securitypermission.controller;

import com.example.securitypermission.security.CurrentUser;
import com.example.securitypermission.security.JwtProvider;
import com.example.securitypermission.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.example.securitypermission.entity.User;
import com.example.securitypermission.payload.LoginDTO;
import com.example.securitypermission.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;


//    @PostMapping("/register")

    @GetMapping("/test")
    public HttpEntity<?> getTest() {
        return ResponseEntity.ok("Test");
    }

    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));

        User user = (User) authenticate.getPrincipal();

        String token = jwtProvider.generateToken(user.getUsername(), user.getRoles());
        return ResponseEntity.ok(token);
    }
}

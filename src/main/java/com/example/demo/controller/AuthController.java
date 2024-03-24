package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserReq;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.UserLoginExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager manager;
    private final JWTUtils jwtUtils;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserReq user) throws UserLoginExistException {
        userService.save(new User(user.getLogin(), user.getPassword()));

        return ResponseEntity.ok(jwtUtils.generateToken(user.getLogin()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserReq user) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getLogin(),
                user.getPassword()
        ));

        return ResponseEntity.ok(jwtUtils.generateToken(user.getLogin()));
    }

    @ExceptionHandler
    public ResponseEntity<?> userLoginExist(UserLoginExistException e) {
        return ResponseEntity.badRequest().body("user login already exist");
    }
}

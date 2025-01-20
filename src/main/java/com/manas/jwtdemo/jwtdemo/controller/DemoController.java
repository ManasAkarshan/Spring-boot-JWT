package com.manas.jwtdemo.jwtdemo.controller;

import com.manas.jwtdemo.jwtdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private JwtUtil jwtUtil;

    private String authHeader;

    @GetMapping("/api/demo")
    public String demo(@RequestHeader("Authorization") String authHeader) {
        this.authHeader = authHeader;
        String token = authHeader.replace("Bearer ", "");
        if (jwtUtil.validateToken(token)) {
            return "Welcome to the secured endpoint!";
        } else {
            return "Unauthorized";
        }
    }
}

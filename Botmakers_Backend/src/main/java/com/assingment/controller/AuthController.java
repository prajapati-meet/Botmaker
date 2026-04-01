package com.assingment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assingment.dto.AuthResponse;
import com.assingment.dto.LoginRequest;
import com.assingment.dto.RegisterRequest;
import com.assingment.entity.User;
import com.assingment.security.JwtUtil;
import com.assingment.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
	private final AuthService authServices;
	private final JwtUtil jwtUtil;
	
	public AuthController(AuthService authServices, JwtUtil jwtutil) {
		super();
		this.authServices = authServices;
		this.jwtUtil = jwtutil;
	}

	@PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authServices.register(request);
        return "User Registered";
    }
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {

		  System.out.println("LOGIN API HIT");
	    User user = authServices.login(request);

	    String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

	    AuthResponse response = new AuthResponse();
	    response.setToken(token);
	    response.setRole(user.getRole().name());

	    return response;
	}
}

package com.assingment.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assingment.dto.LoginRequest;
import com.assingment.dto.RegisterRequest;
import com.assingment.entity.User;
import com.assingment.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	
	public AuthServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void register(RegisterRequest request) {
		User user= new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        
        userRepo.save(user);
	}

	

	@Override
	public User login(LoginRequest request) {
		User user = userRepo.findByEmail(request.getEmail())
		        .orElseThrow(() -> new RuntimeException("User not found"));
		
		if(!passwordEncoder.matches(request.getPassword(),user.getPassword()))
		{
			throw new RuntimeException("Invalid Credentials");
		}

		return user;
	}

}

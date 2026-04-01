package com.assingment.service;

import com.assingment.dto.LoginRequest;
import com.assingment.dto.RegisterRequest;
import com.assingment.entity.User;

public interface AuthService {
	void register(RegisterRequest request);
	User login(LoginRequest request);
}

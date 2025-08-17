package com.tibil.apigateway.service;

import com.tibil.apigateway.dto.LoginRequest;
import com.tibil.apigateway.dto.LoginResponse;
import com.tibil.apigateway.dto.RegisterRequest;

public interface AuthService {
	void register(RegisterRequest request);

	LoginResponse login(LoginRequest request);
}

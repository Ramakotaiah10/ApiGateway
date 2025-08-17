package com.tibil.apigateway.controller;

import com.tibil.apigateway.dto.LoginRequest;
import com.tibil.apigateway.dto.LoginResponse;
import com.tibil.apigateway.dto.RegisterRequest;
import com.tibil.apigateway.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest req) {
		authService.register(req);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
		return ResponseEntity.ok(authService.login(req));
	}
}

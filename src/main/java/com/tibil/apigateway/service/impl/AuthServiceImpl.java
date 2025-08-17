package com.tibil.apigateway.service.impl;

import com.tibil.apigateway.dto.LoginRequest;
import com.tibil.apigateway.dto.LoginResponse;
import com.tibil.apigateway.dto.RegisterRequest;
import com.tibil.apigateway.entity.AppUser;
import com.tibil.apigateway.repository.AppUserRepository;
import com.tibil.apigateway.security.JwtUtil;
import com.tibil.apigateway.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

	private final AppUserRepository userRepo;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authManager;
	private final JwtUtil jwt;


	public AuthServiceImpl(AppUserRepository userRepo, PasswordEncoder encoder, AuthenticationManager authManager,
			JwtUtil jwt) {
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.authManager = authManager;
		this.jwt = jwt;
	}

	@Override
	@Transactional
	public void register(RegisterRequest request) {
		if (userRepo.existsByUsername(request.getUsername())) {
			throw new IllegalArgumentException("Username already exists");
		}
		AppUser u = AppUser.builder().username(request.getUsername()).password(encoder.encode(request.getPassword()))
				.roles(Set.of("USER")).build();
		userRepo.save(u);
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		String token = jwt.generateToken(request.getUsername());
		return new LoginResponse(token);
	}
}

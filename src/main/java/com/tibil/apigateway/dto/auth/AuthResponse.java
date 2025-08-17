package com.tibil.apigateway.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
	private String token;
	private String tokenType; // e.g., "Bearer"
	private String username;
}
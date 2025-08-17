package com.tibil.apigateway.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private final Key key;
	private final long expirationMs;

	public JwtUtil(@Value("${security.jwt.secret}") String secret,
			@Value("${security.jwt.expiration-ms:86400000}") long expirationMs) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.expirationMs = expirationMs;
	}

	public String generateToken(String username) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expirationMs);
		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiry)
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return parseClaims(token).getBody().getSubject();
	}

	public boolean validate(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	private Jws<Claims> parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	}
}
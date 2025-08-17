package com.tibil.apigateway.security;

import com.tibil.apigateway.entity.AppUser;
import com.tibil.apigateway.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private final AppUserRepository appUserRepository;

	public AppUserDetailsService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!appUserRepository.existsByUsername(username)) {
			throw new UsernameNotFoundException("User not found "+username);
		}
		
		AppUser user = appUserRepository.findByUsername(username);
		return new AppUserDetails(user);
	}
}
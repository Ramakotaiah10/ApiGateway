package com.tibil.apigateway.repository;

import com.tibil.apigateway.entity.ApiUrl;
import com.tibil.apigateway.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiUrlRepository extends JpaRepository<ApiUrl, Long> {
	List<ApiUrl> findByAppUser(AppUser appUser);

	Optional<ApiUrl> findByIdAndAppUser(Long id, AppUser appUser);
}

package com.tibil.apigateway.repository;

import com.tibil.apigateway.entity.ApiResponse;
import com.tibil.apigateway.entity.ApiUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiResponseRepository extends JpaRepository<ApiResponse, Long> {
	List<ApiResponse> findByApiUrl(ApiUrl apiUrl);

	@Query("SELECT r FROM ApiResponse r WHERE r.apiUrl.appUser.username = :username")
	List<ApiResponse> findByUsername(String username);
	
}

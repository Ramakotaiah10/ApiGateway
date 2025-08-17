package com.tibil.apigateway.service;

import com.tibil.apigateway.dto.ApiUrlDto;
import com.tibil.apigateway.entity.ApiResponse;
import com.tibil.apigateway.entity.ApiUrl;

import java.util.List;

public interface ApiUrlService {
	ApiUrl saveApiUrl(ApiUrlDto apiUrlDto, String username);

	List<ApiUrl> getAllApiUrls(String username);

	ApiResponse fetchAndStoreResponse(Long urlId, String username);
}

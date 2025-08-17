package com.tibil.apigateway.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibil.apigateway.dto.ApiUrlDto;
import com.tibil.apigateway.entity.ApiResponse;
import com.tibil.apigateway.entity.ApiUrl;
import com.tibil.apigateway.entity.AppUser;
import com.tibil.apigateway.factory.ApiParserFactory;
import com.tibil.apigateway.parser.ApiParser;
import com.tibil.apigateway.repository.ApiResponseRepository;
import com.tibil.apigateway.repository.ApiUrlRepository;
import com.tibil.apigateway.repository.AppUserRepository;
import com.tibil.apigateway.service.ApiUrlService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiUrlServiceImpl implements ApiUrlService {

    private final ApiUrlRepository apiUrlRepository;
    private final ApiResponseRepository apiResponseRepository;
    private final AppUserRepository appUserRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ApiUrlServiceImpl(ApiUrlRepository apiUrlRepository,
                             ApiResponseRepository apiResponseRepository,
                             AppUserRepository appUserRepository) {
        this.apiUrlRepository = apiUrlRepository;
        this.apiResponseRepository = apiResponseRepository;
        this.appUserRepository = appUserRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ApiUrl saveApiUrl(ApiUrlDto apiUrlDto, String username) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        ApiUrl apiUrl = ApiUrl.builder()
                .url(apiUrlDto.getUrl())
                .appUser(user)
                .createdAt(java.time.Instant.now())
                .build();

        return apiUrlRepository.save(apiUrl);
    }

    @Override
    public List<ApiUrl> getAllApiUrls(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        return apiUrlRepository.findByAppUser(user);
    }

    @Override
    public ApiResponse fetchAndStoreResponse(Long urlId, String requestingUsername) {
        AppUser user = appUserRepository.findByUsername(requestingUsername);
        if (user == null) {
            throw new RuntimeException("User not found: " + requestingUsername);
        }

        ApiUrl apiUrl = apiUrlRepository.findByIdAndAppUser(urlId, user)
                .orElseThrow(() -> new RuntimeException("API URL not found or not owned by user"));

        String raw;
        try {
            raw = restTemplate.getForObject(apiUrl.getUrl(), String.class);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to call external API: " + ex.getMessage(), ex);
        }

        ApiParser parser = ApiParserFactory.getParser(apiUrl.getUrl());
        String parsed;
        try {
            Object parsedObj = parser.parse(raw);
            if (parsedObj == null) {
                parsed = null;
            } else if (parsedObj instanceof String) {
                parsed = (String) parsedObj;
            } else {
                parsed = objectMapper.writeValueAsString(parsedObj);
            }
        } catch (Exception e) {
            parsed = "PARSE_ERROR: " + e.getMessage();
        }

        ApiResponse response = ApiResponse.builder()
                .apiUrl(apiUrl)
                .rawResponse(raw)
                .parsedData(parsed)
                .createdAt(LocalDateTime.now())
                .build();

        return apiResponseRepository.save(response);
    }
}

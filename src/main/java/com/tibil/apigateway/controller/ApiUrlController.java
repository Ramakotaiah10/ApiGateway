package com.tibil.apigateway.controller;

import com.tibil.apigateway.dto.ApiResponseDto;
import com.tibil.apigateway.dto.ApiUrlDto;
import com.tibil.apigateway.entity.ApiResponse;
import com.tibil.apigateway.entity.ApiUrl;
import com.tibil.apigateway.mapper.ApiResponseMapper;
import com.tibil.apigateway.mapper.ApiUrlMapper;
import com.tibil.apigateway.service.ApiUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/urls")
public class ApiUrlController {

    private final ApiUrlService apiUrlService;

    public ApiUrlController(ApiUrlService apiUrlService) {
        this.apiUrlService = apiUrlService;
    }

    @PostMapping
    public ResponseEntity<ApiUrlDto> addApiUrl(@RequestBody ApiUrlDto apiUrlDto, Authentication authentication) {
        String username = authentication.getName();
        ApiUrl savedUrl = apiUrlService.saveApiUrl(apiUrlDto, username);
        return ResponseEntity.created(URI.create("/api/urls/" + savedUrl.getId()))
                .body(ApiUrlMapper.toDto(savedUrl));
    }

    @GetMapping
    public ResponseEntity<List<ApiUrlDto>> getAllApiUrls(Authentication authentication) {
        String username = authentication.getName();
        List<ApiUrl> urls = apiUrlService.getAllApiUrls(username);
        return ResponseEntity.ok(
                urls.stream().map(ApiUrlMapper::toDto).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}/fetch")
    public ResponseEntity<ApiResponseDto> fetchAndStore(@PathVariable("id") Long id, Authentication authentication) {
        String username = authentication.getName();
        ApiResponse saved = apiUrlService.fetchAndStoreResponse(id, username);
        return ResponseEntity.ok(ApiResponseMapper.toDto(saved));
    }
}


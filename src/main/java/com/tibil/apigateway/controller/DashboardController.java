package com.tibil.apigateway.controller;

import com.tibil.apigateway.dto.ApiResponseDto;
import com.tibil.apigateway.entity.ApiResponse;
import com.tibil.apigateway.mapper.ApiResponseMapper;
import com.tibil.apigateway.repository.ApiResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ApiResponseRepository apiResponseRepository;

    @GetMapping("/responses")
    public ResponseEntity<List<ApiResponseDto>> getResponses(Authentication authentication) {
        String username = authentication.getName();
        List<ApiResponse> responses = apiResponseRepository.findByUsername(username);

        List<ApiResponseDto> dtos = responses.stream()
                .map(ApiResponseMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }
}

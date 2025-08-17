package com.tibil.apigateway.mapper;

import com.tibil.apigateway.dto.ApiResponseDto;
import com.tibil.apigateway.entity.ApiResponse;

import java.time.format.DateTimeFormatter;

public class ApiResponseMapper {

	public static ApiResponseDto toDto(ApiResponse entity) {
		return ApiResponseDto.builder().id(entity.getId()).apiUrlId(entity.getApiUrl().getId())
				.rawResponse(entity.getRawResponse()).parsedData(entity.getParsedData())
				.createdAt(entity.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).build();
	}
}

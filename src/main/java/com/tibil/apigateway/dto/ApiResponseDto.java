package com.tibil.apigateway.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
	private Long id;
	private Long apiUrlId;
	private String rawResponse;
	private String parsedData;
	private String createdAt;
}

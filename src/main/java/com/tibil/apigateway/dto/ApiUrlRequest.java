package com.tibil.apigateway.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApiUrlRequest {
	@NotBlank
	private String url;
}

package com.tibil.apigateway.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiUrlDto {
	private Long id;
	private String url;
	private String description; // optional
}

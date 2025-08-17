package com.tibil.apigateway.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "api_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Many responses for one ApiUrl
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "api_url_id")
	private ApiUrl apiUrl;

	@Column(name = "raw_response", columnDefinition = "TEXT")
	private String rawResponse;

	@Column(name = "parsed_data", columnDefinition = "TEXT")
	private String parsedData;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
}

package com.tibil.apigateway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
@JsonIgnoreType
@Entity
@Table(name = "api_urls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	@Column(nullable = false, length = 2048)
	private String url;

	private Instant createdAt;
}

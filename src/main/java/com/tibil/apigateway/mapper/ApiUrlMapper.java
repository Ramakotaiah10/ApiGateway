
package com.tibil.apigateway.mapper;

import com.tibil.apigateway.dto.ApiUrlDto;
import com.tibil.apigateway.entity.ApiUrl;

public class ApiUrlMapper {
	public static ApiUrlDto toDto(ApiUrl entity) {
		return ApiUrlDto.builder().id(entity.getId()).url(entity.getUrl()).build();
	}
}

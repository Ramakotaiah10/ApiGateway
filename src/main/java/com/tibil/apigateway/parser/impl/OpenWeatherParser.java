package com.tibil.apigateway.parser.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibil.apigateway.parser.ApiParser;

public class OpenWeatherParser implements ApiParser {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object parse(String jsonResponse) throws Exception {
		JsonNode root = mapper.readTree(jsonResponse);
		JsonNode main = root.path("main");
		if (!main.isMissingNode()) {
			double temp = main.path("temp").asDouble(Double.NaN);
			double humidity = main.path("humidity").asDouble(Double.NaN);
			return String.format("temp=%s, humidity=%s", temp, humidity);
		}
		return root.toString();
	}
}

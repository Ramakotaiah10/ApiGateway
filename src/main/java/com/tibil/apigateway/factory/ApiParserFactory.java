package com.tibil.apigateway.factory;

import com.tibil.apigateway.parser.ApiParser;
import com.tibil.apigateway.parser.impl.JsonPlaceholderParser;
import com.tibil.apigateway.parser.impl.OpenWeatherParser;

public class ApiParserFactory {

	/**
	 * Basic heuristic factory. Extend with more rules/config-driven mapping if
	 * needed.
	 */
	public static ApiParser getParser(String url) {
		String u = url.toLowerCase();
		if (u.contains("jsonplaceholder.typicode.com")) {
			return new JsonPlaceholderParser();
		} else if (u.contains("api.openweathermap.org") || u.contains("openweathermap")) {
			return new OpenWeatherParser();
		} else {
			// Default parser: returns raw JSON as-is (simple wrapper)
			return jsonResponse -> jsonResponse;
		}
	}
}

package com.tibil.apigateway.parser;

public interface ApiParser {
	/**
	 * Parse raw JSON string and return an object representing parsed data.
	 * Implementations should keep parsing-specific logic (return can be POJO or
	 * String).
	 */
	Object parse(String jsonResponse) throws Exception;
}

package com.tibil.apigateway.parser.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibil.apigateway.parser.ApiParser;

public class JsonPlaceholderParser implements ApiParser {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object parse(String jsonResponse) throws Exception {
		// jsonplaceholder endpoints return objects or lists. We'll try to extract a
		// useful summary.
		JsonNode node = mapper.readTree(jsonResponse);
		if (node.isArray()) {
			// return titles list if objects have "title"
			StringBuilder sb = new StringBuilder();
			for (JsonNode item : node) {
				if (item.has("title")) {
					sb.append(item.get("title").asText()).append(" | ");
				}
			}
			return sb.length() > 0 ? sb.toString() : node.toString();
		} else {
			if (node.has("title"))
				return node.get("title").asText();
			return node.toString();
		}
	}
}

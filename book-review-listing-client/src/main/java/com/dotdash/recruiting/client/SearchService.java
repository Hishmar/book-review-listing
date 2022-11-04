package com.dotdash.recruiting.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SearchService {
	

	public GoodReadsResult search(String query, String field, String page) {
		//The base URI here would ideally be in a configuration file or similar for a release version, but will work as a regular string here
		return WebClient.create("http://localhost:8080")
		.get()
		.uri(uriBuilder -> uriBuilder.path("/goodreadsapi")
			.queryParam("query", query)
			.queryParam("field", field)
			.queryParam("page", page)
			.build())
		.retrieve()
		.bodyToMono(GoodReadsResult.class).block();
				
	}
	
}

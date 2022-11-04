package com.dotdash.recruiting.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class GoodReadsController {

	@Autowired
	GoodReadsService service;
	
	@GetMapping("/goodreadsapi")
	private GoodReadsResult getBookList(@RequestParam("query") String query, @RequestParam("field") String field, @RequestParam("page") int page ){
		try {
			return service.getBookList(query, field, page);
		} catch (Exception e) {
			return null;
		}
	}
}

package com.dotdash.recruiting.api;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString

public class GoodReadsResult {
	private @Getter @Setter List<GoodReadsBook> books;
	private @Getter @Setter int page;
	private @Getter @Setter String query;
	private @Getter @Setter String searchField;
}

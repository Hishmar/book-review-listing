package com.dotdash.recruiting.api;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodReadsBook {
	private @Getter @Setter String author;
	private @Getter @Setter String title;
	private @Getter @Setter String link;
}

package com.dotdash.recruiting.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodReadsBook {
	private @Getter @Setter String author;
	private @Getter @Setter String title;
	private @Getter @Setter String link;
}

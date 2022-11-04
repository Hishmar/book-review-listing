package com.dotdash.recruiting.client;

import java.util.Comparator;

public class SortByTitle implements Comparator<GoodReadsBook> {
	public int compare(GoodReadsBook a, GoodReadsBook b) {
		return a.getTitle().compareTo(b.getTitle());
	}

}
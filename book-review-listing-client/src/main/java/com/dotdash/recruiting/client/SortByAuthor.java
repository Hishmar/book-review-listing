package com.dotdash.recruiting.client;

import java.util.Comparator;

public class SortByAuthor implements Comparator<GoodReadsBook> {
	public int compare(GoodReadsBook a, GoodReadsBook b) {
		return a.getAuthor().compareTo(b.getAuthor());
	}

}
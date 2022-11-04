package com.dotdash.recruiting.client;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookReviewListingClientApplication implements CommandLineRunner {
	
	private SearchService searchService;
	
	public BookReviewListingClientApplication(SearchService searchService) {
		this.searchService = searchService;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BookReviewListingClientApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	//putting this one up here since its much longer and requires more formatting than the single lines later
	private static String helpStatement = "Commands:\n"
										+ "--help: Display commands\n"
										+ "-s, --search: asks for search criteria and prints result\n"
										+ "--sort: sorts by either 'author' or 'title' in current data set\n"
										+ "-p: returns the specified page of a current search\n"
										+ "-h, --host: returns the hostname of the API server\n"
										+ "-q: quits program\n";
	
	private String prettyPrintList(List<GoodReadsBook> list) {
		String prettyString = "";
		for(int i=0; i<list.size(); i++) {
		prettyString = prettyString.concat(list.get(i).getTitle() + "\n" + list.get(i).getAuthor() + "\n" +list.get(i).getLink()+"\n-------\n");
		}
		return prettyString;
	}
	@Override
	public void run(String... args) throws Exception {
		
		GoodReadsResult result = null;
		String query = "";
		String field = "";
		String page = "";
		boolean cont = true;
		
		
		Scanner scanner = new Scanner(System.in);
		while(cont) {
			System.out.println("Good Reads Client, Enter a Command");
			String line = scanner.nextLine();
			switch(line) {
			case "--help":
				System.out.println(helpStatement);
				break;
			case "-s":
			case "--search":
				System.out.println("Enter Query");
				query = scanner.nextLine();
				System.out.println("Enter Search Field");
				field = scanner.nextLine();
				System.out.println("Enter Page");
				page = scanner.nextLine();
				result = searchService.search(query, field, page);
				System.out.println(prettyPrintList(result.getBooks()));
				break;
			case "--sort":
				if(result != null) {
					System.out.println("Sort by author or title?");
					String sort = scanner.nextLine();
						if(sort.equals("author")) {
							//Currently sorts alphabetically by first word. 
							//Ideally you'd do it by last name but not every author on good reads is a first and last name. You'd have to do some hefty regex so this'll do for now.
							Collections.sort(result.getBooks(), new SortByAuthor());
						}
						if(sort.equals("title")) {
							Collections.sort(result.getBooks(), new SortByTitle());
						}
					System.out.println(prettyPrintList(result.getBooks()));
				}else {
					System.out.println("No results to sort");
				}
				break;
			case "-p":
				if(result != null) {
					System.out.println(result.getPage());
				}else {
					System.out.println("No Results Currently");
				}
				break;
			case "-h":
			case "--host":
				//in a real system this should be a configuration variable but as I have no plans to create multiple environments or have this change I'll just print it here.
				System.out.println("localhost:8080");
				break;
			case "-q":
				cont = false;
				break;
			default:
				System.out.println("Not a recognized command");
			}
		}
		scanner.close();
	}

}

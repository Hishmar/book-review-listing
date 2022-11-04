package com.dotdash.recruiting.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodReadsService {
	private final Logger logger = LoggerFactory.getLogger(GoodReadsService.class);
	public GoodReadsResult getBookList(String query, String field, int page) throws Exception {
		GoodReadsResult result = new GoodReadsResult();
		result.setPage(page);
		result.setQuery(query);
		result.setSearchField(field);
		try {
		String URL = buildURL(query, field, page);
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);
        
        doc.getDocumentElement().normalize();
        
        NodeList nodeList = doc.getElementsByTagName("best_book");
        
        List<GoodReadsBook> books = new ArrayList<>();
        
        for (int i = 0; i < nodeList.getLength(); i++) {
        	Node node = nodeList.item(i);
        	
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element elem = (Element) node;
        		GoodReadsBook book = new GoodReadsBook();
        		book.setTitle(elem.getElementsByTagName("title").item(0).getTextContent());
        		book.setLink(elem.getElementsByTagName("image_url").item(0).getTextContent());
        		book.setAuthor(elem.getElementsByTagName("name").item(0).getTextContent());
        		
        		books.add(book);
        	}
        }
        
        result.setBooks(books);
		}catch (Exception ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
		return result;
	}
	
	
	private String buildURL(String query, String field, int page) {
		//in a "real" program this would be a URI builder with the key injected at runtime. This works though for a demo where there are no additional paramaters or a need to expand to consider.
		return "https://www.goodreads.com/search.xml?key=RDfV4oPehM6jNhxfNQzzQ&q="+query+"&search[field]="+field+"&page="+page;
	}

}


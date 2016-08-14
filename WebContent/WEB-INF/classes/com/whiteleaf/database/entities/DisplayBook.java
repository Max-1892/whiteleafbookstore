package com.whiteleaf.database.entities;

import com.whiteleaf.database.dao.AuthorDAO;
import com.whiteleaf.database.dao.CategoryDAO;
import com.whiteleaf.database.dao.PublisherDAO;

public class DisplayBook {
	public static String displayBook(Book book){
		String url = "/whiteleafbookstore/CartServlet?action=cart&amp;quantity=1&amp;productCode=" + book.getISBN();
		
		String content = "<h2>" + book.getTitle() + "</h2>"
				+ "<img src=\"resources/images/"+ book.getIllustration() + "\">" 
				+ "<p> By " + AuthorDAO.getAuthorFromId(book.getAuthorId()) + " | " + book.getISBN() + " | " + book.getDate().getYear() + "</p>"
				+ "<p> $" + book.getPrice() + " | " + book.getPageCount() + " pages | " + PublisherDAO.getPublisherFromId(book.getPublisherId()) + "</p>"
				+ "<p><b>" + CategoryDAO.getCategoryFromId(book.getCategoryId()) + "</b> " + book.getSummary() +"</p>"
				+ "<a href=\"" + url + "\">Add to Cart</a>";
		return content;
	}
	
	public static String displayBookResult(Book book){
		String url = "/whiteleafbookstore/SearchServlet?action=ISBN&amp;searchWord=" + book.getISBN();
		String display = "<tr><a href=\"" + url + "\"><td colspan=\"3\"><b>" + book.getTitle() + "</b></td></a><tr>";
		display = display + "<tr><td><a href=\"" + url + "\"><img src=\"" + book.getIllustration() + "\"></a></td><td>"+ AuthorDAO.getAuthorFromId(book.getAuthorId()) +
				"</td><td>" + book.getPrice() +"</td></tr>";
		return display;
	}
	
	
}

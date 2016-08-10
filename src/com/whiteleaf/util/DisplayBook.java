package com.whiteleaf.util;

import com.whiteleaf.database.entities.Author;
import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.Category;
import com.whiteleaf.database.entities.Publisher;
import java.text.SimpleDateFormat;

public class DisplayBook {
    public static String displayBook(Book book, Author author, Category category, Publisher publisher){
        String url = "/CartServlet?action=cart&amp;quantity=1&amp;productCode=" + book.getISBN();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String publicationDate = dateFormat.format(book.getDate());

        String content = "<h2>" + book.getTitle() + "</h2>"
                        + "<img src=\""+ book.getIllustration()+ "\">" 
                        + "<p> By " + author.getName() + " | " + book.getISBN() + " | " + publicationDate + "</p>"
                        + "<p> $" + book.getPrice() + " | " + book.getPageCount() + " pages | " + publisher.getPublisher() + "</p>"
                        + "<p><b>" + category.getCategory() + "</b> " + book.getSummary() +"</p>"
                        + "<a href=\"" + url + "\">Add to Cart</a>";
        return content;
    }

    public static String displayBookResult(Book book, Author author){
        String url = "/SearchServlet?action=ISBN&amp;searchWord=" + book.getISBN();
        String display = "<tr><a href=\"" + url + "\"><td colspan=\"3\"><b>" + book.getTitle() + "</b></td></a><tr>";
        display = display + "<tr><td><a href=\"" + url + "\"><img src=\"" + book.getIllustration() + "\"></a></td><td>"+ author.getName() +
                        "</td><td>" + book.getPrice() +"</td></tr>";
        return display;
    }
}

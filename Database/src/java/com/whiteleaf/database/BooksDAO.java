package com.whiteleaf.database;

import com.whiteleaf.database.entities.Book;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikilbou1
 */
public class BooksDAO {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT category FROM categories";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    int authorId = rs.getInt("author_id");
                    String ISBN = rs.getString("ISBN");
                    Date publicationDate = rs.getDate("publication_date");
                    int publisherId = rs.getInt("publisher_id");
                    int pageCount = rs.getInt("page_count");
                    String summary = rs.getString("summary");
                    Blob illustration = rs.getBlob("illustration");
                    int categoryId = rs.getInt("category_id");
                    Book temp = new Book(title, authorId, ISBN, publicationDate,
                                            publisherId, pageCount, summary,
                                            illustration, categoryId);
                    books.add(temp);
                }
                return books;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    
    public static boolean addBook(Book book) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO BOOKS (title, author_id, isbn, publication_date, publisher_id, page_count, summary, illustration, category)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, String.valueOf(book.getAuthorId()));
            ps.setString(3, book.getISBN());
            ps.setString(4, book.getDate().toString());
            ps.setString(5, String.valueOf(book.getPublisherId()));
            ps.setString(6, String.valueOf(book.getPageCount()));
            ps.setString(7, book.getSummary());
            ps.setString(8, book.getIllustration().toString());
            ps.setString(9, String.valueOf(book.getCategoryId()));
            int result = ps.executeUpdate();
            if (result > 0)
                return true;
            return false;
        } catch (SQLException e) {
            return false;
        } finally {
            cp.freeConnection(c);
        }
    }
}

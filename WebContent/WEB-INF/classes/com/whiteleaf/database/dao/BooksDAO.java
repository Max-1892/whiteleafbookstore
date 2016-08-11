package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.Author;
import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.Category;
import com.whiteleaf.database.entities.Publisher;
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

        String query = "SELECT * FROM books";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int authorId = rs.getInt("author_id");
                    String ISBN = rs.getString("ISBN");
                    Date publicationDate = rs.getDate("publication_date");
                    int publisherId = rs.getInt("publisher_id");
                    int pageCount = rs.getInt("page_count");
                    String summary = rs.getString("summary");
                    Blob illustration = rs.getBlob("illustration");
                    int categoryId = rs.getInt("category_id");
                    Book temp = new Book(id, title, authorId, ISBN,
                                        publicationDate, publisherId, pageCount,
                                        summary, illustration, categoryId);
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

    public static Book getBookFromId(int bookId) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM books WHERE id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int authorId = rs.getInt("author_id");
                String ISBN = rs.getString("ISBN");
                Date publicationDate = rs.getDate("publication_date");
                int publisherId = rs.getInt("publisher_id");
                int pageCount = rs.getInt("page_count");
                String summary = rs.getString("summary");
                Blob illustration = rs.getBlob("illustration");
                int categoryId = rs.getInt("category_id");
                return new Book(id, title, authorId, ISBN,
                                    publicationDate, publisherId, pageCount,
                                    summary, illustration, categoryId);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static List<Book> getBooksFromAuthor(Author author) {
        return getBooksWhere("author_id", author.getId());
    }

    public static List<Book> getBooksInCategory(Category category) {
        return getBooksWhere("category_id", category.getId());
    }

    public static List<Book> getBooksFromPublisher(Publisher publisher) {
        return getBooksWhere("publisher_id", publisher.getId());
    }

    public static List<Book> getBooksWhere(String columnName, int columnValue) {
        List<Book> books = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT title, author_id, ISBN, publication_date,"
                    + " publisher_id, page_count, summary, illustration, category_id"
                    + " FROM books"
                    + " WHERE ?=?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, columnName);
            ps.setInt(2, columnValue);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int authorId = rs.getInt("author_id");
                    String ISBN = rs.getString("ISBN");
                    Date publicationDate = rs.getDate("publication_date");
                    int publisherId = rs.getInt("publisher_id");
                    int pageCount = rs.getInt("page_count");
                    String summary = rs.getString("summary");
                    Blob illustration = rs.getBlob("illustration");
                    int categoryId = rs.getInt("category_id");
                    Book temp = new Book(id, title, authorId, ISBN,
                                        publicationDate, publisherId, pageCount,
                                        summary, illustration, categoryId);
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

        String query = "INSERT INTO BOOKS (title, author_id, isbn,"
                    + " publication_date, publisher_id, page_count, summary,"
                    + " illustration, category)"
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
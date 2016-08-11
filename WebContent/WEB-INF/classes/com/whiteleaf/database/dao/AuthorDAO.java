package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikilbou1
 */
public class AuthorDAO {
    public static List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM authors";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Author temp = new Author(rs.getInt("id"), rs.getString("name"));
                    authors.add(temp);
                }
                return authors;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static Author getAuthorByName(String authorName) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM authors WHERE name LIKE ?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, authorName);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                return new Author(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static Author getAuthorFromId(int authorId) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM authors WHERE id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, authorId);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                return new Author(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addAuthor(Author author) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO AUTHORS (name) VALUES (?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, author.getName());
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

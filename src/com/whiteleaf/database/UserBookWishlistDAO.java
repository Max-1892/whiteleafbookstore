package com.whiteleaf.database;

import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.User;
import com.whiteleaf.database.entities.UserBookWishList;
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
public class UserBookWishlistDAO {
    public static List<UserBookWishList> getUsersWishList(User user) {
        ArrayList<UserBookWishList> wishList = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user_book_wishlist WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int bookId = rs.getInt("book_id");
                    Book book = BooksDAO.getBookFromId(bookId);
                    wishList.add(new UserBookWishList(id, book, user));
                }
                return wishList;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addBookToWishList(User user, Book book) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result = 0;

        String query = "INSERT INTO user_book_wishlist (user_id, book_id) VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.setInt(2, book.getId());
            result = ps.executeUpdate();
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

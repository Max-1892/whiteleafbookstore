package com.whiteleaf.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ikilbou1
 */
public class WhiteleafUsersDAO {
    public static boolean addWhiteleafUser(String name, String password) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result;

        String query = "INSERT INTO whiteleaf_users (name, password)"
                + " VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
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

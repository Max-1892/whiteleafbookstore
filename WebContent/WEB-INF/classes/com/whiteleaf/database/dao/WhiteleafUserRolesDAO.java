package com.whiteleaf.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ikilbou1
 */
public class WhiteleafUserRolesDAO {
    public static boolean addWhiteleafUserRole(String name, String role) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result;

        String query = "INSERT INTO whiteleaf_user_roles (name, role)"
                + " VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, role);
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
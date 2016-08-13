package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.UserName;
import com.whiteleaf.database.entities.UserPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ikilbou1
 */
public class UserPasswordDAO {
	private static int id = 0;
    public static UserPassword getUserPassword(UserName user) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user_passwords WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                return new UserPassword(rs.getInt("id"), rs.getInt("user_id"), rs.getString("password"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addPassword(UserPassword password) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT user_passwords (user_id, password)) VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, password.getUserId());
            ps.setString(2, password.getPassword());
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

    public static boolean updatePassword(UserPassword password) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE user_passwords SET password=? WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, password.getPassword());
            ps.setInt(2, password.getUserId());
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

	public static int getNextId() {
		return UserPasswordDAO.id + 1;
	}
}

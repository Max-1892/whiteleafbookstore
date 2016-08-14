package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.UserName;
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
public class UserNamesDAO {
    public static List<UserName> getUserNames() {
        List<UserName> userNames = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user_names";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    UserName temp = new UserName(id, name);
                    userNames.add(temp);
                }
                return userNames;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static UserName getUserByName(String userName) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user_names WHERE name=?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.first();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                return new UserName(id, name);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addUserName(UserName user) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO user_names (name) VALUES (?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, user.getName());
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

    public static UserName addUserName(String userName) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO user_names (name) VALUES (?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, userName);
            int result = ps.executeUpdate();
            if (result > 0)
                return getUserByName(userName);
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }
}

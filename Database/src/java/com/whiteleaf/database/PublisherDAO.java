package com.whiteleaf.database;

import com.whiteleaf.database.entities.Publisher;
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
public class PublisherDAO {
    public static List<Publisher> getPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT name FROM publishers";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Publisher temp = new Publisher(rs.getString("name"));
                    publishers.add(temp);
                }
                return publishers;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }
}
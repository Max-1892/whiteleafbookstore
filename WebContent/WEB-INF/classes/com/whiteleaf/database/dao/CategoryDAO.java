package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.Category;
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
public class CategoryDAO {
    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM categories";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Category temp = new Category(rs.getInt("id"), rs.getString("category"));
                    categories.add(temp);
                }
                return categories;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static Category getCategoryFromId(int categoryId) {
        return getCategoryWhere("id", String.valueOf(categoryId));
    }

    public static Category getCategoryByName(String categoryName) {
        return getCategoryWhere("category", categoryName);
    }

    public static Category getCategoryWhere(String columnName, String columnValue) {
                ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM categories WHERE " + columnName + "=?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, columnValue);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                return new Category(rs.getInt("id"), rs.getString("category"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }
}

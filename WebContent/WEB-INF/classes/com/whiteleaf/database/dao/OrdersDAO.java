package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.Orders;
import com.whiteleaf.database.entities.User;
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
public class OrdersDAO {
    public static List<Orders> getUsersOrders(User user) {
        ArrayList<Orders> orders = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM orders WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int cardId = rs.getInt("credit_card_id");
                    int shippingId = rs.getInt("shipping_address_id");
                    int billingId = rs.getInt("billing_address_id");
                    String books = rs.getString("ordered_books");
                    Double totalCost = rs.getDouble("total_cost");
                    orders.add(new Orders(id, userId, cardId, shippingId, billingId, books, totalCost));
                }
                return orders;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addUsersOrders(Orders order) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result;

        String query = "INSERT INTO orders (user_id, credit_card_id,"
                + " shipping_address_id, billing_address_id, ordered_books,"
                + " total_cost)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProviderId());
            ps.setInt(3, order.getShippingAddressId());
            ps.setInt(4, order.getBillingAddressId());
            ps.setString(5, order.getOrderedBooks());
            ps.setDouble(6, order.getTotalCost());
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

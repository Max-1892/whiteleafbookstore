package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.UserName;
import com.whiteleaf.database.entities.UserAddress;
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
public class UserAddressDAO {
	private static int id = 0;

    public static List<UserAddress> getUserBillingAddress(UserName user) {
        return getAddressList("user_billing_address", user);
    }

    public static List<UserAddress> getUserEmailAddress(UserName user) {
        return getAddressList("user_email_address", user);
    }

    public static List<UserAddress> getUserShippingAddress(UserName user) {
        return getAddressList("user_shipping_address", user);
    }

    public static List<UserAddress> getAddressList(String table, UserName user) {
        List<UserAddress> addresses = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM " + table + " WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    String address = rs.getString("address");
                    UserAddress temp = new UserAddress(id, userId, address);
                    addresses.add(temp);
                }
                return addresses;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addUserBillingAddress(UserAddress address) {
        return addAddress("user_billing_address", address);
    }

    public static boolean addUserEmailAddress(UserAddress address) {
        return addAddress("user_email_address", address);
    }

    public static boolean addUserShippingAddress(UserAddress address) {
        return addAddress("user_shipping_address", address);
    }

    public static boolean addAddress(String table, UserAddress address) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO " + table + " (address) VALUES (?) WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, address.getAddress());
            ps.setInt(2, address.getUserId());
            int result = ps.executeUpdate();
            if (result > 0) {
            	UserAddressDAO.id++;
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean updateUserBillingAddress(UserAddress address) {
        return addAddress("user_billing_address", address);
    }

    public static boolean updateUserEmailAddress(UserAddress address) {
        return addAddress("user_email_address", address);
    }

    public static boolean updateUserShippingAddress(UserAddress address) {
        return addAddress("user_shipping_address", address);
    }

    public static boolean updateAddress(String table, UserAddress address) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE " + table + " SET address=? WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, address.getAddress());
            ps.setInt(2, address.getUserId());
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
		return UserAddressDAO.id + 1;
	}
}

package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.CreditCardProvider;
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
public class CreditCardProviderDAO {
    private static List<CreditCardProvider> getCreditCardProviders() {
        ArrayList<CreditCardProvider> providers = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM credit_card_providers";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    providers.add(new CreditCardProvider(rs.getInt("id"), rs.getString("name")));
                }
                return providers;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    private static CreditCardProvider getCreditCardProviderById(int providerId) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM credit_card_providers WHERE id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, providerId);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                return new CreditCardProvider(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }
}

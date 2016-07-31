package com.whiteleaf.database;

import com.whiteleaf.database.entities.User;
import com.whiteleaf.database.entities.UserCreditCards;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikilbou1
 */
public class UserCreditCardsDAO {
    public static List<UserCreditCards> getUsersCreditCards(User user) {
        ArrayList<UserCreditCards> creditCards = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user_credit_cards WHERE user_id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int providerId = rs.getInt("card_provider_id");
                    String cardNumber = rs.getString("card_number");
                    Date expirationDate = rs.getDate("expiration_date");
                    creditCards.add(new UserCreditCards(id, userId, providerId, cardNumber, expirationDate));
                }
                return creditCards;
            }
            return null;
        } catch(SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addUsersCreditCard(UserCreditCards creditCard) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result;

        String query = "INSERT INTO user_credit_cards (user_id,"
                + " card_provider_id, card_number, expiration_date)"
                + " VALUES (?, ?, ?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, creditCard.getUserId());
            ps.setInt(2, creditCard.getProviderId());
            ps.setString(3, creditCard.getCardNumber());
            ps.setDate(4, creditCard.getExpirationDate());
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

    public static boolean updateUsersCreditCard(UserCreditCards creditCard) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result;

        String query = "UPDATE user_credit_cards"
                + " SET expiration_date=?" 
                + " WHERE id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setDate(1, creditCard.getExpirationDate());
            ps.setInt(2, creditCard.getId());
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

    public static boolean removeUsersCreditCards(UserCreditCards creditCard) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        int result;

        String query = "DELETE FROM user_credit_cards WHERE id=?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, creditCard.getId());
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

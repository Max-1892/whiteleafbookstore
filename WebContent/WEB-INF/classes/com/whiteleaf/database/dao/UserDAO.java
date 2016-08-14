package com.whiteleaf.database.dao;

import com.whiteleaf.database.entities.CreditCardProvider;
import com.whiteleaf.database.entities.UserAddress;
import com.whiteleaf.database.entities.UserCreditCards;
import com.whiteleaf.database.entities.UserName;
import com.whiteleaf.database.entities.UserPassword;
import com.whiteleaf.database.util.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikilbou1
 */
public class UserDAO {
	private static int userId = 0;

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "user_names.id as u_id, user_names.name, b.id as b_id, b.address as billing_address,"
                + " s.id as s_id, s.shipping_address, e.id as e_id, e.address as email_address,"
                + " c.id as c_id, c.card_provider_id, c.card_number, c.expiration_date"
                + " JOIN user_shipping_address s ON user_names.id=s.user_id"
                + " JOIN user_billing_address b ON user_names.id=b.user_id"
                + " JOIN user_email_address e ON user_names.id=e.user_id"
                + " JOIN user_credit_cards c ON user_names.id=c.user_id"
                + " GROUP BY user_names.id";
        try {
            ps = c.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs != null) {
                int currentUserId = -1;
                User temp = null;
                while (rs.next()) {
                    int uId = rs.getInt("u_id");
                    String name = rs.getString("name");
                    int bId = rs.getInt("b_id");
                    String billingAddress = rs.getString("billing_address");
                    int sId = rs.getInt("s_id");
                    String shippingAddress = rs.getString("shipping_address");
                    int eId = rs.getInt("e_id");
                    String emailAddress = rs.getString("email_address");
                    int cId = rs.getInt("c_id");
                    int cpId = rs.getInt("card_provider_id");
                    String cardNumber = rs.getString("card_namber");
                    Date expirationDate = rs.getDate("expiration_date");
                    // create new user if necessary
                    if (uId != currentUserId) {
                        currentUserId = uId;
                        temp = new User();
                        temp.setUserName(new UserName(uId, name));
                        temp.setEmailAddress(new UserAddress(eId, uId, emailAddress));
                        users.add(temp);
                    }
                    assert temp != null;
                    temp.addBillingAddress(new UserAddress(bId, uId, billingAddress));
                    temp.addShippingAddress(new UserAddress(sId, uId, shippingAddress));
                    temp.addCreditCards(new UserCreditCards(cId, uId, cpId, cardNumber, expirationDate));
                }
                return users;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            cp.freeConnection(c);
        }
    }

    public static boolean addUser(UserName userName, UserPassword password, UserAddress billingAddress,
            UserAddress shippingAddress, UserAddress emailAddress, UserCreditCards creditCard) {
        //ConnectionPool cp = ConnectionPool.getInstance();
        //Connection c = cp.getConnection();
        //PreparedStatement ps = null;

        if (!UserNamesDAO.addUserName(userName))
            return false;
        if (!UserPasswordDAO.addPassword(password))
        	return false;
        if (!UserAddressDAO.addUserBillingAddress(billingAddress))
            return false;
        if (!UserAddressDAO.addUserShippingAddress(shippingAddress))
            return false;
        if (!UserAddressDAO.addUserEmailAddress(emailAddress))
            return false;
        if (!UserCreditCardsDAO.addUsersCreditCard(creditCard))
            return false;
        UserDAO.userId++;
        return true;
    }

    public static boolean addUser(String userName, String userPassword,
            String billingAddress, String shippingAddress, String emailAddress,
            String cardNumber, String cardProvider, String expirationDate) {
        UserName name = UserNamesDAO.addUserName(userName);
        if (name != null) {
            // add password
            UserPassword password = new UserPassword();
            password.setUserId(name.getId());
            password.setPassword(userPassword);
            if (!UserPasswordDAO.addPassword(password))
                return false;
            // add billing
            UserAddress billing = new UserAddress();
            billing.setUserId(name.getId());
            billing.setAddress(billingAddress);
            if (!UserAddressDAO.addUserBillingAddress(billing))
                return false;
            // add shipping
            UserAddress shipping = new UserAddress();
            shipping.setUserId(name.getId());
            shipping.setAddress(shippingAddress);
            if (!UserAddressDAO.addUserShippingAddress(shipping))
                return false;
            // add e-mail
            UserAddress email = new UserAddress();
            email.setUserId(name.getId());
            email.setAddress(emailAddress);
            if (!UserAddressDAO.addUserEmailAddress(email))
                return false;
            // add credit card
            UserCreditCards creditCard = new UserCreditCards();
            creditCard.setUserId(name.getId());
            CreditCardProvider provider = CreditCardProviderDAO.getCreditCardProviderByName(cardProvider);
            creditCard.setProviderId(provider.getId());
            // parse the date string into sql Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            Date date;
            try {
                java.util.Date temp = dateFormat.parse(expirationDate);
                date = new Date(temp.getTime());
            } catch (ParseException ex) {
                return false;
            }
            creditCard.setExpirationDate(date);
            creditCard.setCardNumber(cardNumber);
            if (!UserCreditCardsDAO.addUsersCreditCard(creditCard))
                return false;
            return true;
        }
        return false;
    }

    public static int getNextUserId() {
    	return UserDAO.userId + 1;
    }
}

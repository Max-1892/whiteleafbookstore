package com.whiteleaf.database.util;

import com.whiteleaf.database.entities.UserAddress;
import com.whiteleaf.database.entities.UserCreditCards;
import com.whiteleaf.database.entities.UserName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikilbou1
 */
public class User {
    private UserName userName;
    private List<UserAddress> shippingAddresses = new ArrayList<>();
    private List<UserAddress> billingAddresses = new ArrayList<>();
    private UserAddress emailAddress;
    private List<UserCreditCards> creditCards = new ArrayList<>();

    public User() {
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public List<UserAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void addShippingAddress(UserAddress shippingAddresses) {
        this.shippingAddresses.add(shippingAddresses);
    }

    public List<UserAddress> getBillingAddresses() {
        return billingAddresses;
    }

    public void addBillingAddress(UserAddress billingAddresses) {
        this.billingAddresses.add(billingAddresses);
    }

    public UserAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(UserAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<UserCreditCards> getCreditCards() {
        return creditCards;
    }

    public void addCreditCards(UserCreditCards creditCards) {
        this.creditCards.add(creditCards);
    }

    
}

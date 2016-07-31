package com.whiteleaf.database.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ikilbou1
 */
public class UserCreditCards {
    private int id;
    private int userId;
    private int providerId;
    private String cardNumber;
    private Date expirationDate;

    public UserCreditCards() {
    }

    public UserCreditCards(int id, int userId, int providerId, String cardNumber, Date expirationDate) {
        this.id = id;
        this.userId = userId;
        this.providerId = providerId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.userId;
        hash = 47 * hash + this.providerId;
        hash = 47 * hash + Objects.hashCode(this.cardNumber);
        hash = 47 * hash + Objects.hashCode(this.expirationDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserCreditCards other = (UserCreditCards) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.providerId != other.providerId) {
            return false;
        }
        if (!Objects.equals(this.cardNumber, other.cardNumber)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        return true;
    }
}

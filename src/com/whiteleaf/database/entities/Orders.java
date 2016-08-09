package com.whiteleaf.database.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ikilbou1
 */
public class Orders implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int userId;
    private int providerId;
    private int shippingAddressId;
    private int billingAddressId;
    private String orderedBooks;
    private Double totalCost;

    public Orders() {
    }

    public Orders(int userId, int providerId, int shippingAddressId, int billingAddressId, String orderedBooks, Double totalCost) {
        this.userId = userId;
        this.providerId = providerId;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
        this.orderedBooks = orderedBooks;
        this.totalCost = totalCost;
    }

    public Orders(int id, int userId, int providerId, int shippingAddressId, int billingAddressId, String orderedBooks, Double totalCost) {
        this.id = id;
        this.userId = userId;
        this.providerId = providerId;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
        this.orderedBooks = orderedBooks;
        this.totalCost = totalCost;
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

    public int getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(int shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public int getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(int billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getOrderedBooks() {
        return orderedBooks;
    }

    public void setOrderedBooks(String orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.userId;
        hash = 17 * hash + this.providerId;
        hash = 17 * hash + this.shippingAddressId;
        hash = 17 * hash + this.billingAddressId;
        hash = 17 * hash + Objects.hashCode(this.orderedBooks);
        hash = 17 * hash + Objects.hashCode(this.totalCost);
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
        final Orders other = (Orders) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.providerId != other.providerId) {
            return false;
        }
        if (this.shippingAddressId != other.shippingAddressId) {
            return false;
        }
        if (this.billingAddressId != other.billingAddressId) {
            return false;
        }
        if (!Objects.equals(this.orderedBooks, other.orderedBooks)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return true;
    }
}

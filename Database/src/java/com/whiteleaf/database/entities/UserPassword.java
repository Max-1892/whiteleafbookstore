package com.whiteleaf.database.entities;

/**
 *
 * @author ikilbou1
 */
public class UserPassword {
    private int userId;
    private String password;

    public UserPassword() {
    }

    public UserPassword(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

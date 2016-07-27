package com.whiteleaf.database.entities;

/**
 *
 * @author ikilbou1
 */
public class UserBookWishList {
    private int userId;
    private int bookId;

    public UserBookWishList() {
    }

    public UserBookWishList(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}

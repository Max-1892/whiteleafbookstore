package com.whiteleaf.database.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ikilbou1
 */
public class UserBookWishList implements Serializable {
    private int id;
    private Book book;
    private UserName user;

    public UserBookWishList() {
    }

    public UserBookWishList(int id, Book book, UserName user) {
        this.id = id;
        this.book = book;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserName getUser() {
        return user;
    }

    public void setUser(UserName user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.book);
        hash = 89 * hash + Objects.hashCode(this.user);
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
        final UserBookWishList other = (UserBookWishList) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
}

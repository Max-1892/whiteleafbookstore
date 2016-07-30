package com.whiteleaf.database.entities;

import java.sql.Blob;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ikilbou1
 */
public class Book {
    private int id;
    private String title;
    private int authorId;
    private String ISBN;
    private Date date;
    private int publisherId;
    private int pageCount;
    private String summary;
    private Blob illustration;
    private int categoryId;

    public Book() {
    }

    public Book(int id, String title, int authorId, String ISBN, Date date,
                int publisherId, int pageCount, String summary,
                Blob illustration, int categoryId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.ISBN = ISBN;
        this.date = date;
        this.publisherId = publisherId;
        this.pageCount = pageCount;
        this.summary = summary;
        this.illustration = illustration;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Blob getIllustration() {
        return illustration;
    }

    public void setIllustration(Blob illustration) {
        this.illustration = illustration;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "BOOK (" + this.title + ","
                + String.valueOf(this.authorId) + ","
                + this.ISBN + ","
                + String.valueOf(this.categoryId) + ","
                + this.date + ","
                + this.pageCount + ","
                + String.valueOf(this.publisherId) + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + this.authorId;
        hash = 79 * hash + Objects.hashCode(this.ISBN);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + this.publisherId;
        hash = 79 * hash + this.pageCount;
        hash = 79 * hash + Objects.hashCode(this.summary);
        hash = 79 * hash + Objects.hashCode(this.illustration);
        hash = 79 * hash + this.categoryId;
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
        final Book other = (Book) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.publisherId != other.publisherId) {
            return false;
        }
        if (this.pageCount != other.pageCount) {
            return false;
        }
        if (!Objects.equals(this.summary, other.summary)) {
            return false;
        }
        if (!Objects.equals(this.illustration, other.illustration)) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return true;
    }
}
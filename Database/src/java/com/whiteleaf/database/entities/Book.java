package com.whiteleaf.database.entities;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author ikilbou1
 */
public class Book {
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

    public Book(String title, int authorId, String ISBN, Date date, int publisherId, int pageCount, String summary, Blob illustration, int categoryId) {
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
}
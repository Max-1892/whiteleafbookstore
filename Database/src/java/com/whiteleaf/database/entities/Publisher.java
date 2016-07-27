package com.whiteleaf.database.entities;

/**
 *
 * @author ikilbou1
 */
public class Publisher {
    private String publisher;

    public Publisher() {
    }

    public Publisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "PUBLISHER (" + this.publisher + ")";
    }
}

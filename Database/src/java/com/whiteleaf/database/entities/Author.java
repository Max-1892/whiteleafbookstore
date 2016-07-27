package com.whiteleaf.database.entities;

/**
 *
 * @author ikilbou1
 */
public class Author {
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AUTHOR (" + this.name + ")";
    }
}

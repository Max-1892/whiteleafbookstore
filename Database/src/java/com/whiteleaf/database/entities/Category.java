package com.whiteleaf.database.entities;

/**
 *
 * @author ikilbou1
 */
public class Category {
    private String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CATEGORY (" + this.category + ")";
    }
}

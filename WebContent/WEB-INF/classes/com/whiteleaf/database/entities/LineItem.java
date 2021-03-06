package com.whiteleaf.database.entities;

import java.io.Serializable;
import java.text.NumberFormat;

public class LineItem implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book book;
    private int quantity;
    
    public LineItem() {}
    
    public void setBook(Book b)
    {
        book = b;
    }

    public Book getBook()
    { 
        return book;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public int getQuantity()
    { 
        return quantity; 
    }
    
    public double getTotal()
    { 
        double total = book.getPrice().doubleValue() * quantity;
        return total;
    }
    
    public String getTotalCurrencyFormat()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }
}
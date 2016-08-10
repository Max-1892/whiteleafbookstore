package com.whiteleaf.database.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<LineItem> items;
    
    public Cart()
    {
        items = new ArrayList<LineItem>();
    }
    
    public ArrayList<LineItem> getItems()
    { 
        return items;
    }
    
    public int getCount()
    { 
        return items.size();
    }
    
    public void addItem(LineItem item)
    {
        String ISBN = item.getBook().getISBN();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++)
        {
            LineItem lineItem = items.get(i);
            if (lineItem.getBook().getISBN().equals(ISBN))
            {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
    
    public void removeItem(LineItem item)
    {
        String code = item.getBook().getISBN();
        for (int i = 0; i < items.size(); i++)
        {
            LineItem lineItem = items.get(i);
            if (lineItem.getBook().getISBN().equals(code))
            {
                items.remove(i);
                return;
            }
        }
    }
}
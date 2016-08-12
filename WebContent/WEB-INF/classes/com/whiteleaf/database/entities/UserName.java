package com.whiteleaf.database.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ikilbou1
 */
public class UserName implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id = -1;
    private String name = "";

    public UserName() {
    }

    public UserName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String  toString() {
        return "USER (" + this.name + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final UserName other = (UserName) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    public boolean isValid() {
    	return (id != -1) && (!name.equals(""));
    }
}

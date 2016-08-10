package com.whiteleaf.database.entities;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ProductIO {

    public static Book getBook(String code, String filepath) {
        try {
            File file = new File(filepath);
            BufferedReader in
                    = new BufferedReader(
                            new FileReader(file));

            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String productCode = t.nextToken();
                if (code.equalsIgnoreCase(productCode)) {
                    String description = t.nextToken();
                    double price = Double.parseDouble(t.nextToken());
                    Book p = new Book();
                    p.setISBN(code);
                    p.setTitle(description);
                    p.setPrice(new BigDecimal(price));
                    in.close();
                    return p;
                }
                line = in.readLine();
            }
            in.close();
            return null;
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }
}
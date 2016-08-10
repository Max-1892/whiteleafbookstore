package tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.Cart;
import com.whiteleaf.database.entities.LineItem;

import java.util.*;
import java.io.IOException;

public class CartTag extends BodyTagSupport {

    private ArrayList<LineItem> lineItems;
    private Iterator iterator;
    private LineItem item;

    @Override
    public int doStartTag() {
        Cart cart = (Cart) pageContext.findAttribute("cart");
        lineItems = cart.getItems();
        if (lineItems.size() <= 0) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_BUFFERED;
        }
    }

    @Override
    public void doInitBody() throws JspException {
        iterator = lineItems.iterator();
        if (iterator.hasNext()) {
            item = (LineItem) iterator.next();
            this.setItemAttributes(item);
        }
    }

    private void setItemAttributes(LineItem item) {
        Book p = item.getBook();
        pageContext.setAttribute(
                "productCode", p.getISBN());
        pageContext.setAttribute(
                "productDescription", p.getTitle());
        pageContext.setAttribute(
                "productPrice", p.getPrice());
        pageContext.setAttribute(
                "quantity", new Integer(item.getQuantity()));
        pageContext.setAttribute(
                "total", item.getTotalCurrencyFormat());
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            if (iterator.hasNext()) {
                item = (LineItem) iterator.next();
                this.setItemAttributes(item);
                return EVAL_BODY_AGAIN;
            } else {
                JspWriter out = bodyContent.getEnclosingWriter();
                bodyContent.writeOut(out);
                return SKIP_BODY;
            }
        } catch (IOException ioe) {
            System.err.println("error in doAfterBody " + ioe.getMessage());
            return SKIP_BODY;
        }
    }
}
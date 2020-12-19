package polytech.source.models;

import java.io.Serializable;

public class OrderPosition implements Serializable {
    private static final long serialVersionUID = 1L;

    private Product product;
    private double price;
    private int quantity;

    public OrderPosition(Product product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductVendorCode() {
        return product.getVendorCode();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public String getProductColor() {
        return product.getProductColor();
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }
}

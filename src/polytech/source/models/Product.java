package polytech.source.models;
import java.math.BigDecimal;

public class Product {

    private long vendorCode;
    private String productName;
    private String productColor;
    private BigDecimal productPrice;
    private int stockQuantity;

    public Product(long vendorCode, String productName, String productColor, BigDecimal productPrice, int stockQuantity) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(vendorCode);
        sb.append(";");
        sb.append(productName);
        sb.append(";");
        sb.append(productPrice.toString());
        sb.append(";");
        sb.append(stockQuantity);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Product) {
            Product obj = (Product) o;
            if (obj.vendorCode == this.vendorCode
                    && obj.productName.equals(this.productName)
                    && obj.productPrice.equals(this.productPrice)
                    && obj.stockQuantity == this.stockQuantity) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public long getVendorCode() {
        return vendorCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


}

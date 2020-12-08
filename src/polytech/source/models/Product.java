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


    public long getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(long vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

//    @Override
//    public List<Product> loadObjects(String path) throws IOException {
//        List<Product> productsList = new ArrayList<>(); //создаем список пустой
//        FileReader reader = new FileReader(path);
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        while (bufferedReader.ready()) {
//            String row = bufferedReader.readLine();
//            String[] splittedRow = row.split(";"); //из списка продуктов получил элементы
//            long vendorCode = Long.valueOf(splittedRow[0]);
//            String productName = splittedRow[1];
//            String productColor = splittedRow[2];
//            BigDecimal productPrice = new BigDecimal(splittedRow[3]);
//            int stockQuantity = Integer.valueOf(splittedRow[4]);
//            Product product = new Product(vendorCode, productName, productColor, productPrice, stockQuantity);
//            productsList.add(product);
//        }
//        bufferedReader.close();
//        return productsList;
//    }
}

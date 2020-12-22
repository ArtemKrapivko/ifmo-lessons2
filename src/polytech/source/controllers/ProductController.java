package polytech.source.controllers;

import polytech.source.models.Product;
import polytech.source.storages.products_storage.ProductsStorage;
import resources.configs.Config;

import java.io.IOException;
import java.util.List;


public class ProductController {

    public ProductsStorage productStorage = new ProductsStorage();
    private List<Product> products = productStorage.loadProductsFromFile(Config.PATH_PRODUCTS);

    public ProductController() throws IOException, ClassNotFoundException {
    }

    public void save() throws IOException {
        productStorage.saveProductToCSVFile(products, Config.PATH_PRODUCTS);

    }


    public List<Product> loadProductFromUserFile(String pathToFile) throws IOException {
        this.products = productStorage.loadProductsFromFile(pathToFile);
        return this.products;
    }

    public int getProductsCount() {
        return this.products.size();
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public Object[] getProductsForChooseList() {
        Object[] result = new Object[products.size()];

        for (int i = 0; i < result.length; i++) {
            Product p = products.get(i);
            if (p.getStockQuantity() > 0) {
                result[i] = p.getVendorCode() + "; " + p.getProductName() + ";  " + p.getProductColor() + ";  " + p.getProductPrice();
            }
        }

        return result;
    }

    public Product searchProductByVendorCode(long vendorCoder) {
        for(Product p : products) {
            if(p.getVendorCode() == vendorCoder) {
                return p;
            }
        }
        return null;
    }

    public void decreaseProductAmount(Product p, int numberToDecrease) {
        int currentStockQuantity = p.getStockQuantity();
        if (currentStockQuantity - numberToDecrease < 0) {
            throw new RuntimeException(
                    String.format("Попытка уменьшить количество товара на величину большую чем на складе\nПродукт %s", p.toString()));
        }
        p.setStockQuantity(currentStockQuantity - numberToDecrease);
    }
}

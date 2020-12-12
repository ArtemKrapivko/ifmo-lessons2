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
        products.addAll(productStorage.loadProductsFromFile(pathToFile));
        return this.products;
    }

    public int getProductsCount() {
        return this.products.size();
    }

    public Product getProduct(int index) {
        return products.get(index);
    }
}

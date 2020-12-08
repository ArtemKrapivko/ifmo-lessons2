package polytech.source.controllers;

import polytech.source.models.Product;
import polytech.source.storages.products_storage.ProductsStorage;

import java.io.IOException;
import java.util.List;

public class ProductController {

    public ProductsStorage productsStorage = new ProductsStorage();

    public List<Product> loadProducts() throws IOException {
        List<Product> loadProduct = productsStorage.loadProducts(); //из файла берем список продуктов
        return loadProduct;                                         //возвращаем этот список
    }
}

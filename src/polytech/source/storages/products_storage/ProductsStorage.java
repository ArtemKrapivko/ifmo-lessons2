package polytech.source.storages.products_storage;

import polytech.source.models.Product;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static resources.configs.Config.PATH_PRODUCTS;


public class ProductsStorage {

    public void saveProductToCSVFile(List<Product> productsList, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product p : productsList) {
                writer.write(p.getVendorCode() + ";" + p.getProductName() + ";" +p.getProductColor()+";" + p.getProductPrice() + ";" + p.getStockQuantity());
                writer.newLine();
            }
        }
    }


    public List<Product> loadProductsFromFile(String filePath) throws IOException {
        List<Product> listPproducts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] splittedRow = row.split(";");
                    Product p = new Product(Long.valueOf(splittedRow[0]), splittedRow[1], splittedRow[2],
                            BigDecimal.valueOf(Long.parseLong(splittedRow[3])), Integer.valueOf(splittedRow[4]));
                    listPproducts.add(p);
                }
            }
        return listPproducts;
    }
}



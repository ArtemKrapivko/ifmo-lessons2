package polytech.source.storages.products_storage;

import polytech.source.models.Product;

import java.io.IOException;
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


    public void createProduct(Product create) throws IOException {
        Set<BasicFileAttributes> params = new HashSet<>();
        Path savePath = Paths.get(PATH_PRODUCTS);

        if (Files.exists(savePath)) {
            System.out.println("File is exist");
        } else {
            Files.createFile(savePath);
        }
    }

    public void saveProduct(Product save) throws IOException {
        Path myPath = Paths.get(PATH_PRODUCTS);
        List<String> line = new ArrayList<>();
        Files.write(myPath, line, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        System.out.println("Data written");
    }


    public void saveProducts(List<Product> productList) throws IOException {
        Path myPath = Paths.get(PATH_PRODUCTS);
        List<String> lines = new ArrayList<>();
        Files.write(myPath, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        System.out.println("All data written");
    }


    public List<Product> loadProducts() throws IOException {
        Path myPath = Paths.get(PATH_PRODUCTS);
        List<String> lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);
//        lines.forEach(line -> System.out.println(line));
        List<Product> list = new ArrayList<>();
        lines.forEach(line -> {                 //фиг.когда много выражений
            String[] splitLines = line.split(";");
            Long vc = Long.valueOf(splitLines[0]);
            String pn = String.valueOf(splitLines[1]);
            String pc = String.valueOf(splitLines[2]);
            BigDecimal pp = new BigDecimal(splitLines[3]);
            Integer sq = Integer.valueOf(splitLines[4]);
            Product p = new Product(vc, pn, pc, pp, sq);
            list.add(p);                                //заполняю список
        });
    return list;                                        //возвращаю список
    }


}


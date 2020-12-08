package polytech.source.storages.orders_storage;

import polytech.source.models.Order;
import polytech.source.models.OrderPosition;
import polytech.source.models.OrderStatus;
import polytech.source.models.Product;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

import static resources.configs.Config.PATH_ORDERS;


public class OrdersStorage {

    public void createOrder(Order create) throws IOException {
        Set<BasicFileAttributes> params = new HashSet<>();
        Path savePath = Paths.get(PATH_ORDERS);

        if (Files.exists(savePath)) {
            System.out.println("File is exist");
        } else {
            Files.createFile(savePath);
        }
    }

    public void saveOrders(List<Order> orders) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(PATH_ORDERS))) {
            oos.writeObject(orders);
        }
    }


//        Path myPath = Paths.get(PATH_ORDERS);
//        List<String> line = new ArrayList<>();
//        Files.write(myPath, line, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
//        System.out.println("Data written");
//    }


    public List<Order> loadOrders() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(PATH_ORDERS))) {
            return (List<Order>) ois.readObject();
        }
    }
}


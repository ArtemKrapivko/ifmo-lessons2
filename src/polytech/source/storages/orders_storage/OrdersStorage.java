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

    public void saveOrders(List<Order> orders) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(PATH_ORDERS))) {
            for (Order o : orders) {
                oos.writeObject(o);
            }
        }
    }

    public List<Order> loadOrdersWithDeserialization(String filePath) {
        List<Order> ordersList = new ArrayList<>();
        if (Files.exists(Paths.get(PATH_ORDERS))) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(filePath))) {
                Order o = (Order)ois.readObject();
                while(o != null) {
                    ordersList.add(o);
                    o = (Order)ois.readObject();
                }
                return ordersList;
            } catch(Exception ex) {
                return ordersList;
            }
        } else {
            return ordersList;
        }
    }
}


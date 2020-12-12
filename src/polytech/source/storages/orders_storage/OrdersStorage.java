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

    public void saveOrders(List<Order> orders, String path_to_orders) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(PATH_ORDERS))) {
            oos.writeObject(orders);
        }
    }

//    public List<Order> loadOrders() throws IOException, ClassNotFoundException {
//        try (ObjectInputStream ois = new ObjectInputStream(
//                new FileInputStream(PATH_ORDERS))) {
//            return (List<Order>) ois.readObject();
//        }
//    }


    public List<Order> loadOrdersWithDeserialization(String filePath) throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get(PATH_ORDERS))) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(filePath))) {
                return (List<Order>) ois.readObject();
            }
        } else {
            return new ArrayList<>();
        }
    }


//
//
//    public List<Order> loadOrderFromFile(String filePath) throws IOException {
//        List<Order> listOrder = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            while (reader.ready()) {
//                String row = reader.readLine();
//                String[] splittedRow = row.split(";");
//                Order o = new Order(Date.valueOf(splittedRow[0]), splittedRow[1], splittedRow[2], BigDecimal.valueOf(Long.parseLong(splittedRow[3])), Integer.valueOf(splittedRow[4]));
//                listOrder.add(o);
//            }
//        }
//
//        return listOrder;
//    }
}


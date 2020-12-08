package polytech.source.controllers;

import polytech.source.models.Order;
import polytech.source.models.Product;
import polytech.source.storages.orders_storage.OrdersStorage;
import polytech.source.storages.products_storage.ProductsStorage;

import java.io.IOException;
import java.util.List;

public class OrdersController {
    public OrdersStorage ordersStorage = new OrdersStorage();

    public List<Order> loadOrders() throws IOException, ClassNotFoundException {
        List<Order> loadOrder = ordersStorage.loadOrders(); //из файла берем список заказов
        return loadOrder;                                         //возвращаем этот список
    }
}

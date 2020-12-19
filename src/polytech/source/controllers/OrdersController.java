package polytech.source.controllers;

import polytech.source.models.Order;
import polytech.source.models.OrderPosition;
import polytech.source.models.OrderStatus;
import polytech.source.storages.orders_storage.OrdersStorage;
import resources.configs.Config;

import java.io.IOException;
import java.util.*;

public class OrdersController {
    public OrdersStorage ordersStorage = new OrdersStorage();
    private List<Order> orders = ordersStorage.loadOrdersWithDeserialization(Config.PATH_ORDERS);

    private List<Order> clients = new ArrayList<>();
    private Set<Order> order = new HashSet<>();


    public void add(String fioCustomer, String customerPhone, String customerAddress, String discount,
                    OrderStatus orderStatus, List<OrderPosition> orderPositionList) {
        int amountProducts = 0;
        for (OrderPosition op : orderPositionList) {
            amountProducts += op.getQuantity();
        }
        orders.add(new Order(new Date(), fioCustomer, customerPhone, customerAddress, discount, amountProducts, orderStatus,
                new Date(), orderPositionList));
    }

    public List<Order> loadOrders() {
        return ordersStorage.loadOrdersWithDeserialization(Config.PATH_ORDERS);
    }

    public void remove(int index) {
        Order c = orders.remove(index);
    }


    public void save() throws IOException {
        ordersStorage.saveOrders(orders);
    }

    public int getOrdersCount() {
        return this.orders.size();
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }


}

package polytech.source.models;

import java.time.LocalDate;

public class Order {


    private LocalDate dateCreateOrder;
    private String fioCustomer;
    private int customerPhone;
    private String customerAddress;
    private int discount;
    private OrderStatus orderStatus;
    private LocalDate dateSendOrder;
    private OrderPosition OrderPosition; //OrderPosition - product, price, quantity


    public Order(LocalDate dateCreateOrder, String fioCustomer, int customerPhone, String customerAddress, int discount, OrderStatus orderStatus, LocalDate dateSendOrder, OrderPosition orderPosition) {
        this.dateCreateOrder = dateCreateOrder;
        this.fioCustomer = fioCustomer;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.discount = discount;
        this.orderStatus = orderStatus;
        this.dateSendOrder = dateSendOrder;
        OrderPosition = orderPosition;
    }

    public LocalDate getDateCreateOrder() {
        return dateCreateOrder;
    }

    public void setDateCreateOrder(LocalDate dateCreateOrder) {
        this.dateCreateOrder = dateCreateOrder;
    }

    public String getFioCustomer() {
        return fioCustomer;
    }

    public void setFioCustomer(String fioCustomer) {
        this.fioCustomer = fioCustomer;
    }

    public int getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getOrderStatus() {
        return orderStatus.toString(); //вызываю элементы
    }

    public LocalDate getDateSendOrder() {
        return dateSendOrder;
    }

    public void setDateSendOrder(LocalDate dateSendOrder) {
        this.dateSendOrder = dateSendOrder;
    }

    public OrderPosition getOrderPosition() {
        return OrderPosition;
    }

    public void setOrderPosition(OrderPosition orderPosition) {
        OrderPosition = orderPosition;
    }

//    @Override
//    public List<Orders> loadObjects(String path) throws IOException {
//        List<Orders> ordersList = new ArrayList<>(); //создаем список пустой
//        FileReader reader = new FileReader(path);
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        while (bufferedReader.ready()) {
//            String row = bufferedReader.readLine();
//            String[] splittedRow = row.split(";",6); //из списка продуктов получил элементы
////            long vendorCode = Long.valueOf(splittedRow[0]);
////            String productName = splittedRow[1];
////            String productColor = splittedRow[2];
////            BigDecimal productPrice = new BigDecimal(splittedRow[3]);
////            int stockQuantity = Integer.valueOf(splittedRow[4]);
////            Product orders = new Product(vendorCode, productName, productColor, productPrice, stockQuantity);
////            ordersList.add(orders);
//        }
//        bufferedReader.close();
//        return ordersList;
//    }
}


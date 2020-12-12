package polytech.source.models;

import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;


    private Date dateCreateOrder;
    private String fioCustomer;
    private String customerPhone;
    private String customerAddress;
    private String discount;
    private OrderStatus orderStatus;
    private Date dateSendOrder;
    private polytech.source.models.OrderPosition OrderPosition; //OrderPosition - product, price, quantity


    public Order(Date dateCreateOrder, String fioCustomer, String customerPhone, String customerAddress,
                 String discount, OrderStatus orderStatus, Date dateSendOrder, OrderPosition orderPosition) {
        this.dateCreateOrder = dateCreateOrder;
        this.fioCustomer = fioCustomer;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.discount = discount;
        this.orderStatus = orderStatus;
        this.dateSendOrder = dateSendOrder;
        OrderPosition = orderPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dateCreateOrder.toString());
        sb.append(";");
        sb.append(fioCustomer);
        sb.append(";");
        sb.append(customerPhone);
        sb.append(";");
        sb.append(customerAddress);
        sb.append(";");
        sb.append(discount);
        sb.append(";");
        sb.append(orderStatus);
        sb.append(";");
        sb.append(dateSendOrder.toString());
        sb.append(";");
        sb.append(OrderPosition);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Order) {
            Order obj = (Order) o;
            if (obj.dateCreateOrder == this.dateCreateOrder
                    && obj.fioCustomer.equals(this.fioCustomer)
                    && obj.customerPhone.equals(this.customerPhone)
                    && obj.customerAddress == (this.customerAddress)
                    && obj.discount == (this.discount)
                    && obj.orderStatus == (this.orderStatus)
                    && obj.dateSendOrder == (this.dateSendOrder)
                    && obj.OrderPosition == (this.OrderPosition)){
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public Date getDateCreateOrder() {
        return dateCreateOrder;
    }

    public void setDateCreateOrder(Date dateCreateOrder) {
        this.dateCreateOrder = dateCreateOrder;
    }

    public String getFioCustomer() {
        return fioCustomer;
    }

    public void setFioCustomer(String fioCustomer) {
        this.fioCustomer = fioCustomer;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOrderStatus() {
        return orderStatus.toString(); //вызываю элементы
    }

    public Date getDateSendOrder() {
        return dateSendOrder;
    }

    public void setDateSendOrder(Date dateSendOrder) {
        this.dateSendOrder = dateSendOrder;
    }

    public polytech.source.models.OrderPosition getOrderPosition() {
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


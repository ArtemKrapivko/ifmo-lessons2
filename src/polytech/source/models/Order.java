package polytech.source.models;

import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date dateCreateOrder;
    private String fioCustomer;
    private String customerPhone;
    private String customerAddress;
    private String discount;
    private int amountProducts;

    private OrderStatus orderStatus;

    private Date dateSendOrder;
    private List<OrderPosition> orderPositionsList;

    public Order(Date dateCreateOrder, String fioCustomer, String customerPhone, String customerAddress,
                 String discount, int amountProducts, OrderStatus orderStatus, Date dateSendOrder, List<OrderPosition> orderPositionList) {
        this.dateCreateOrder = dateCreateOrder;
        this.fioCustomer = fioCustomer;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.discount = discount;
        this.amountProducts = amountProducts;
        this.orderStatus = orderStatus;
        this.dateSendOrder = dateSendOrder;
        this.orderPositionsList = orderPositionList;
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
        sb.append(amountProducts);
        sb.append(";");
        sb.append(orderStatus);
        sb.append(";");
        sb.append(dateSendOrder.toString());
        sb.append(";");
        sb.append(orderPositionsList);
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
                    && obj.amountProducts == (this.amountProducts)
                    && obj.orderStatus == (this.orderStatus)
                    && obj.dateSendOrder == (this.dateSendOrder)
                ) {
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

    public int getAmountProducts() {
        return amountProducts;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setAmountProducts(int amountProducts) {
        this.amountProducts = amountProducts;
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

    public List<OrderPosition> getOrderPositionList() {
        return orderPositionsList;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderPositionList(List<OrderPosition> selectedProducts) {
        this.orderPositionsList = selectedProducts;
    }


}


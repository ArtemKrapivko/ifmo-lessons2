package polytech.user_interface.uimodels;

import javafx.scene.control.DatePicker;
import polytech.source.controllers.OrdersController;
import polytech.source.controllers.ProductController;
import polytech.source.models.Order;
import polytech.source.models.OrderPosition;
import polytech.source.models.OrderStatus;
import polytech.source.models.Product;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersTableModel implements TableModel {
    private static final String[] COLUMN_HEADERS = {
            "Дата создания ордера",
            "ФИО Покупателя",
            "Телефон покупателя",
            "Адрес доставки",
            "Скидка",
            "Статус заказа",
            "Дата отправки заказа",
            "Позиции заказа"
    };


    private static final Class<?>[] COLUMN_TYPES = {
            Date.class,
            String.class,
            Integer.class,
            String.class,
            Integer.class,
            String.class,
            Date.class,
            String.class,
    };

    private OrdersController oc;
    private List<TableModelListener> listeners;


    public OrdersTableModel(OrdersController oc) {
        this.oc = oc;
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return oc.getOrdersCount();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_HEADERS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_HEADERS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order o = oc.getOrder(rowIndex);
        switch (columnIndex) {
            case 0: return o.getDateCreateOrder();
            case 1: return o.getFioCustomer();
            case 2: return o.getCustomerPhone();
            case 3: return o.getCustomerAddress();
            case 4: return o.getDiscount();
            case 5: return o.getOrderStatus();
            case 6: return o.getDateSendOrder();
            case 7: return o.getOrderPosition();
            default: return null;
        }
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    private void fireTableModelEvent(TableModelEvent e) {
        for(TableModelListener l: listeners)
            l.tableChanged(e);
    }

    public void addOrders(String fioCustomer, String customerPhone,
                          String customerAddress, String discount, OrderStatus orderStatus, OrderPosition orderPosition) {
        oc.add(fioCustomer, customerPhone, customerAddress, discount, orderStatus, orderPosition);
        int rowNum = oc.getOrdersCount() - 1;
        fireTableModelEvent(new TableModelEvent(this, rowNum, rowNum,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    public void editOrders(int index, Date dateCreateOrder, String fioCustomer, String customerPhone,
                           String customerAddress, String discount, String orderStatus, Date dateSendOrder,
                           OrderPosition OrderPosition) {
        Order eo = oc.getOrder(index);
        eo.setDateCreateOrder(dateCreateOrder);
        eo.setFioCustomer(fioCustomer);
        eo.setCustomerPhone(customerPhone);
        eo.setCustomerAddress(customerAddress);
        eo.setDiscount(discount);
        eo.setDateSendOrder(dateSendOrder);
        eo.setOrderPosition(OrderPosition); //поправить на выпадающий список???

        fireTableModelEvent(new TableModelEvent(this,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }


//    public void deleteOrders(int index) {
//        OrdersController.remove(index);
//        fireTableModelEvent(new TableModelEvent(this, index, index,
//                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
//    }


}
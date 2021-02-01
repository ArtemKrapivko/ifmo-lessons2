package polytech.user_interface.uimodels;

import polytech.source.models.OrderPosition;
import polytech.source.models.Product;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderPositionTabModel implements TableModel {
    private static final String[] COLUMN_HEADERS = {
            "Vendor code",
            "Product name",
            "Product color",
            "Price",
            "Amount"
    };

    private static final Class<?>[] COLUMN_TYPES = {
            Long.class,
            String.class,
            String.class,
            Double.class,
            Integer.class
    };

    private List<TableModelListener> listeners;
    private List<OrderPosition> selectedProducts = new ArrayList<>();

    public OrderPositionTabModel() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return selectedProducts.size();
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
        OrderPosition op = selectedProducts.get(rowIndex);
        switch (columnIndex) {
            case 0: return op.getProductVendorCode();
            case 1: return op.getProductName();
            case 2: return op.getProductColor();
            case 3: return op.getPrice();
            case 4: return op.getQuantity();
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

    public void addOrderPosition(Product p, double priceWithDiscount, int amount) {
        selectedProducts.add(new OrderPosition(p, priceWithDiscount, amount));
        int rowNum = selectedProducts.size() - 1;
        fireTableModelEvent(new TableModelEvent(this, rowNum, rowNum,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    public OrderPosition getOrderPosition(int index) {
        return selectedProducts.get(index);
    }

    private void fireTableModelEvent(TableModelEvent e) {
        for(TableModelListener l: listeners)
            l.tableChanged(e);
    }

    public List<OrderPosition> getOrderPositionList() {
        return selectedProducts;
    }


    public void removeOrderPosition(int index) {
        selectedProducts.remove(index);
        fireTableModelEvent(new TableModelEvent(this, index, index,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
    }

    public void update(int row) {
        fireTableModelEvent(new TableModelEvent(this, row, row,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }

    public void setOrderPositionList(List<OrderPosition> orderPositionList) {
        selectedProducts.addAll(orderPositionList);
    }
}

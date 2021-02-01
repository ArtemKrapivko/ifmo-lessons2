package polytech.user_interface.uimodels;

import polytech.source.controllers.ProductController;
import polytech.source.models.Product;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductsTableModel implements TableModel {
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

    private ProductController pc;
    private List<TableModelListener> listeners;

    public ProductsTableModel(ProductController pc) {
        this.pc = pc;
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return pc.getProductsCount();
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
        Product p = pc.getProduct(rowIndex);
        switch (columnIndex) {
            case 0: return p.getVendorCode();
            case 1: return p.getProductName();
            case 2: return p.getProductColor();
            case 3: return p.getProductPrice();
            case 4: return p.getStockQuantity();
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

    public void addProducts() {
        int rowNum = pc.getProductsCount() - 1;
        fireTableModelEvent(new TableModelEvent(this, rowNum, rowNum,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }
}

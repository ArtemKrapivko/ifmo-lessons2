package polytech.user_interface.ui;

import javafx.scene.control.DatePicker;
import polytech.source.controllers.OrdersController;
import polytech.source.controllers.ProductController;
import polytech.source.models.OrderPosition;
import polytech.source.models.OrderStatus;
import polytech.user_interface.uimodels.OrdersTableModel;
import polytech.user_interface.uimodels.ProductsTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainFrame extends JFrame {
    private ProductController pc = new ProductController();
    private OrdersController oc = new OrdersController();

    //    private ProductsTableModel productsTableModel;
    private final OrdersTableModel ordersTableModel;
    private JTable mainTable;

    public MainFrame() throws IOException, ClassNotFoundException {
        super("Продажа и учёт товаров");
        setBounds(300, 200, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
//        this.productsTableModel = new ProductsTableModel(pc);
//        mainTable = new JTable(this.productsTableModel);
        this.ordersTableModel = new OrdersTableModel(oc);
        mainTable = new JTable(this.ordersTableModel);

        c.add(mainTable.getTableHeader(), BorderLayout.NORTH);
        c.add(new JScrollPane(mainTable), BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Продукты/Ордера");
        JMenuItem mi;

        JMenuItem miLoad = new JMenuItem("Загрузить продукты из файла");
        miLoad.addActionListener(this::loadProductsFromFile);
        m.add(miLoad);

        //
        mi = new JMenuItem("Добавить новый заказ");
        //mi.addActionListener(e -> addClient());
        mi.addActionListener(this::addNewOrder);
        m.add(mi);

        mi = new JMenuItem("Изменить заказ");
//        mi.addActionListener(e -> editOrder());
        m.add(mi);
//
        mi = new JMenuItem("Удалить заказ");
//        mi.addActionListener(e -> deleteOrder());
        m.add(mi);


        mb.add(m);
        setJMenuBar(mb);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    pc.save();
//                    pc.saveToCSVFile();
                    pc.save();
                } catch (IOException exception) {
                    throw new RuntimeException("Не удалось сохранить файл");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void loadProductsFromFile(ActionEvent ee) {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        File file = null;
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();
        }
        if (file == null) {
            return;
        }
        try {
            pc.loadProductFromUserFile(file.getAbsolutePath());
//            ProductsTableModel.addProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNewOrder(ActionEvent ee) {
        OrderDialog dlg = new OrderDialog(this);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
        if(dlg.isModalResult()) {
//            String LocalDate = dlg.getLocalDate();
            String fioCustomer = dlg.getFioCustomer();
            String customerPhone = dlg.getCustomerPhone();
            String customerAddress = dlg.getCustomerAddress();
            String discount = dlg.getDiscount();
            OrderStatus orderStatus = OrderStatus.getStatusFromString(dlg.getOrderStatus());
//            String dateSendOrder = dlg.getDateSendOrder();
            String orderPosition = dlg.getOrderPosition();
            try {
                ordersTableModel.addOrders(fioCustomer,customerPhone,customerAddress,
                        discount,orderStatus,new OrderPosition(orderPosition,3,3));
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void add(DatePicker localDate) {
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
//        datePicker.setClearButtonVisible(true);
        add(datePicker);
    }

//todo
//    private void editOrder() {
//        int row = mainTable.getSelectedRow();
//        if(row == -1)
//            return;
//        OrderDialog dlg = new OrderDialog(this);
//        dlg.setLocationRelativeTo(this);
//        dlg.setVisible(true);
//        if(dlg.isModalResult()) {
//            ordersTableModel.editOrders(row,dlg.getLocalDate(), dlg.getFioCustomer(),dlg.getCustomerPhone(),
//                    dlg.getCustomerAddress(),dlg.getDiscount(),dlg.getOrderStatus(),dlg.getDateSendOrder(),
//                    dlg.getOrderPosition());
//        }
//    }


//todo
//    private void deleteOrder() {
//        int row = mainTable.getSelectedRow();
//        if(row == -1)
//            return;
//        String phNum = OrdersController.getOrder(row).getNumber().toString();
//        if(JOptionPane.showConfirmDialog(this,
//                "Are you sure you want to delete a record about " + phNum + "?",
//                "Delete confirmation",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
//            ordersTableModel.deleteOrders(row);
//        }
//    }

}

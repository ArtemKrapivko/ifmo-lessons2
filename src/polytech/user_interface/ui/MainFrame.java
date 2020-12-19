package polytech.user_interface.ui;

import polytech.source.controllers.OrdersController;
import polytech.source.controllers.ProductController;
import polytech.source.models.Order;
import polytech.source.models.OrderPosition;
import polytech.source.models.OrderStatus;
import polytech.user_interface.uimodels.OrdersTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame {
    private ProductController pc = new ProductController();
    private OrdersController oc = new OrdersController();

    private final OrdersTableModel ordersTableModel;
    private JTable mainTable;

    public MainFrame() throws IOException, ClassNotFoundException {
        super("Продажа и учёт товаров");
        setBounds(300, 200, 900, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
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

        mi = new JMenuItem("Добавить новый заказ");
        mi.addActionListener(this::addNewOrder);
        m.add(mi);

        mi = new JMenuItem("Изменить заказ");
        mi.addActionListener(this::editOrder);
        m.add(mi);

        mi = new JMenuItem("Отменить заказ");
        mi.addActionListener(e -> canceldOrder());
        m.add(mi);

        mi = new JMenuItem("Отправить заказ");
        mi.addActionListener(this::shipOrder);
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
                    oc.save();
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
        Object[] options = {"Да","Нет"};
        // Если нажали на кнопку Да, n == 0, иначе 1
        int n = JOptionPane.showOptionDialog(this,
                "Текущий список продуктов будет заменён, продолжить?",
                "Загрузка продуктов",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0) {
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addNewOrder(ActionEvent ee) {
        OrderDialog dlg = new OrderDialog(this, pc);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
        if(dlg.isModalResult()) {
            String fioCustomer = dlg.getFioCustomer();
            String customerPhone = dlg.getCustomerPhone();
            String customerAddress = dlg.getCustomerAddress();
            String discount = dlg.getDiscount();

            List<OrderPosition> orderPositionList = dlg.getOrderPositionList();
            try {
                ordersTableModel.addOrders(fioCustomer,customerPhone,customerAddress,
                        discount,OrderStatus.READY,orderPositionList);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editOrder(ActionEvent ee) {
        int row = mainTable.getSelectedRow();
        if(row == -1)
            return;
        Order order = oc.getOrder(row);
        if (!OrderStatus.READY.toString().equals(order.getOrderStatus())) {
            JOptionPane.showMessageDialog(this, "Заказ не может быть изменён",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OrderDialog dlg = new OrderDialog(this,pc, order);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
        if(dlg.isModalResult()) {
            ordersTableModel.editOrder(row, dlg.getFioCustomer(), dlg.getCustomerPhone(),
                    dlg.getCustomerAddress(), dlg.getDiscount(), dlg.getOrderPositionList());

        }
        ordersTableModel.update(row);
    }

    private void shipOrder(ActionEvent ee) {
        int row = mainTable.getSelectedRow();
        if(row == -1)
            return;
        Order order = oc.getOrder(row);
        if (OrderStatus.READY.toString().equals(order.getOrderStatus())) {
            for (OrderPosition op : order.getOrderPositionList()) {
                pc.decreaseProductAmount(op.getProduct(), op.getQuantity());
            }
            order.setOrderStatus(OrderStatus.DELIVERED);
            JOptionPane.showMessageDialog(this, "Заказ выполнен!",
                    "Error adding Order", JOptionPane.OK_OPTION);
        } else if (OrderStatus.CANCEL.toString().equals(order.getOrderStatus())) {
            JOptionPane.showMessageDialog(this, "Заказ уже отменён!",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        } else if (OrderStatus.DELIVERED.toString().equals(order.getOrderStatus())) {
            JOptionPane.showMessageDialog(this, "Заказ уже доставлен!",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        }
        ordersTableModel.update(row);

    }

    private void canceldOrder() {
        int row = mainTable.getSelectedRow();
        if(row == -1)
            return;
        Order order = oc.getOrder(row);
        if (OrderStatus.READY.toString().equals(order.getOrderStatus())) {
            order.setOrderStatus(OrderStatus.CANCEL);
            JOptionPane.showMessageDialog(this, "Заказ отменён!",
                    "Error adding Order", JOptionPane.OK_OPTION);
        } else if (OrderStatus.CANCEL.toString().equals(order.getOrderStatus())) {
            JOptionPane.showMessageDialog(this, "Заказ уже отменён",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        } else if (OrderStatus.DELIVERED.toString().equals(order.getOrderStatus())) {
            JOptionPane.showMessageDialog(this, "Заказ доставлен и не может быть отменён",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        }
        ordersTableModel.update(row);
    }

}

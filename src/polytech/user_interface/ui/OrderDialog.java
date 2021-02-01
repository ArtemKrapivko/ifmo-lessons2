package polytech.user_interface.ui;

import polytech.source.controllers.ProductController;
import polytech.source.models.Order;
import polytech.source.models.OrderPosition;
import polytech.source.models.Product;
import polytech.user_interface.uimodels.OrderPositionTabModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;

import static resources.configs.Config.DISCOUNT_PRODUCTS;

public class OrderDialog extends JDialog {
    private JTextField fioCustomer;
    private JTextField customerPhone;
    private JTextField customerAddress;
    private JTextField discount;
    private JTextField amountProducts;
    private JButton addAmountToProduct;
    private JButton decreaseAmountToProduct;
    private JTextField OrderPosition;
    private JList listProducts;
    private boolean modalResult;
    private final ProductController productController;
    private final OrderPositionTabModel orderPositionTabModel = new OrderPositionTabModel();
    private JTable selectedProductsInOrder = new JTable(orderPositionTabModel);


    public OrderDialog(MainFrame owner, ProductController productController) {
        super(owner, "Новый заказ", true);
        this.productController = productController;

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel p;

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("ФИО Покупателя "));
        fioCustomer = new JTextField(40);
        p.add(fioCustomer);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Телефон покупателя "));
        customerPhone = new JTextField(11);
        p.add(customerPhone);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Адрес доставки "));
        customerAddress = new JTextField(60);
        p.add(customerAddress);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Скидка "));
        discount = new JTextField(10);
        p.add(discount);
      //  topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Позиции заказа "));
//        OrderPosition = new JTextField(10);
//        p.add(OrderPosition);
        topPanel.add(p);

        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> {//добавить проверки (2)
            if (fioCustomer.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ФИО клиента не заполнено",
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (customerPhone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Телефон клиента не заполнен",
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (customerAddress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Адрес клиента не заполнен",
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                for (int i = 0; i < customerPhone.getText().length(); i++) {
                    if (!Character.isDigit(customerPhone.getText().charAt(i))) {
                        JOptionPane.showMessageDialog(this, "Телефон клиента только цифры",
                                "Error adding Order", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            modalResult = true;
            dispose();
        });
        p.add(btn);

        btn = new JButton("Cancel");
        btn.addActionListener(e -> {
            dispose();
        });
        p.add(btn);

        JPanel addProductsPanel = new JPanel();
        listProducts = new JList(productController.getProductsForChooseList());
        this.listProducts.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.listProducts.setLayoutOrientation(JList.VERTICAL);
        this.listProducts.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(this.listProducts);
        listScroller.setPreferredSize(new Dimension(350, 100));
        addProductsPanel.add(listScroller);

        btn = new JButton("Добавить в заказ");
        btn.addActionListener(this::addProductToOrder);

        JPanel panelWithAmountProducts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelWithAmountProducts.add(new JLabel("Количество товара"));
        this.amountProducts = new JTextField(4);
        panelWithAmountProducts.add(amountProducts);
        amountProducts.setText("1");
        addProductsPanel.add(panelWithAmountProducts);
        addProductsPanel.add(btn);
        topPanel.add(addProductsPanel);

        Box panel = new Box(BoxLayout.Y_AXIS);
        selectedProductsInOrder.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        panel.add(new JScrollPane(selectedProductsInOrder));
        panel.setPreferredSize(new Dimension(100, 200));

        topPanel.add(panel);

        JPanel manageSelectedProductPanel = new JPanel();
        addAmountToProduct = new JButton("+ 1");
        decreaseAmountToProduct = new JButton("- 1");
        manageSelectedProductPanel.add(addAmountToProduct);
        manageSelectedProductPanel.add(decreaseAmountToProduct);
        addAmountToProduct.addActionListener(this::addAmountToSelectedProduct);
        decreaseAmountToProduct.addActionListener(this::decreasedAmountToSelectedProduct);
        topPanel.add(manageSelectedProductPanel);

        topPanel.add(p);
        getContentPane().add(topPanel, BorderLayout.CENTER);


        setResizable(false);
        pack();
    }

    public OrderDialog(MainFrame owner, ProductController productController, Order order) {
        this(owner, productController);
        fioCustomer.setText(order.getFioCustomer());
        customerPhone.setText(order.getCustomerPhone());
        customerAddress.setText(order.getCustomerAddress());
        discount.setText(order.getDiscount());
        orderPositionTabModel.setOrderPositionList(order.getOrderPositionList());
    }

    public String getFioCustomer() {
        return fioCustomer.getText();
    }

    public Product getSelectedProduct(int index) {
        return productController.getProduct(index);
    }

    public String getCustomerPhone() {
        return customerPhone.getText();
    }

    public String getCustomerAddress() {
        return customerAddress.getText();
    }

    public String getDiscount() {
        return discount.getText();
    }

    public List<OrderPosition> getOrderPositionList() {
        return orderPositionTabModel.getOrderPositionList();
    }

    public boolean isModalResult() {
        return modalResult;
    }

    public void addProductToOrder(ActionEvent ee) {
        String selectedItem = (String) this.listProducts.getSelectedValue();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Не выбран товар",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
            return;
        }
        long vendorCode = Long.valueOf(selectedItem.split(";")[0]);
        for (OrderPosition op : orderPositionTabModel.getOrderPositionList()) {
            if (op.getProduct().getVendorCode() == vendorCode) {
                JOptionPane.showMessageDialog(this, "Такой продукт уже был добавлен!",
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Product p = productController.searchProductByVendorCode(vendorCode);

        int requestedAmount = Integer.valueOf(amountProducts.getText() == null || amountProducts.getText().isEmpty() ? "0" : amountProducts.getText());
        if (requestedAmount < 1 || amountProducts == null) {
            JOptionPane.showMessageDialog(this, "Количество меньше единицы",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        } else if (requestedAmount > p.getStockQuantity()) {
            JOptionPane.showMessageDialog(this, "На складе нет столько товара, уменьшите количество",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        } else {
            double newProductPrice = p.getProductPrice().subtract(
                    p.getProductPrice().multiply(new BigDecimal(DISCOUNT_PRODUCTS / 100))).doubleValue();
            orderPositionTabModel.addOrderPosition(p, newProductPrice, requestedAmount);
        }
    }

    private void decreasedAmountToSelectedProduct(ActionEvent actionEvent) {
        int row = selectedProductsInOrder.getSelectedRow();
        if (row == -1)
            return;
        OrderPosition op = orderPositionTabModel.getOrderPosition(row);
        if (op.getQuantity() - 1 == 0) {
            orderPositionTabModel.removeOrderPosition(row);
        } else {
            op.setQuantity(op.getQuantity() - 1);
            orderPositionTabModel.update(row);
        }
    }

    private void addAmountToSelectedProduct(ActionEvent actionEvent) {
        int row = selectedProductsInOrder.getSelectedRow();
        if (row == -1)
            return;
        OrderPosition op = orderPositionTabModel.getOrderPosition(row);
        if (op.getProduct().getStockQuantity() - op.getQuantity() - 1 < 0) {
            JOptionPane.showMessageDialog(this, "На складе нет столько товара, уменьшите количество",
                    "Error adding Order", JOptionPane.ERROR_MESSAGE);
        } else {
            op.setQuantity(op.getQuantity() + 1);
            orderPositionTabModel.update(row);
        }
    }
}

package polytech.user_interface.ui;

import polytech.source.models.OrderStatus;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class OrderDialog extends JDialog {
    private JTextField localDate;
    private JTextField fioCustomer;
    private JTextField customerPhone;
    private JTextField customerAddress;
    private JTextField discount;
    private JComboBox orderStatus;
    private JTextField dateSendOrder;
    private JTextField OrderPosition;

    private boolean modalResult;


    public OrderDialog(MainFrame owner) {
        super(owner, "Новый заказ", true);

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
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Статус заказа "));
//        orderStatus = new JTextField(10);
//        p.add(orderStatus);
//        topPanel.add(p);
        String[] orderStatusString = {OrderStatus.CANCEL.toString(),OrderStatus.DELIVERED.toString(),
                OrderStatus.READY.toString()};
        this.orderStatus = new JComboBox(orderStatusString);
        orderStatus.setSelectedIndex(2);
        orderStatus.setVisible(true);
        p.add(orderStatus);
        topPanel.add(p);

//        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        p.add(new JLabel("Дата отправки заказа "));
//        dateSendOrder = new JTextField(5);
//        p.add(dateSendOrder);
//        topPanel.add(p);


        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Позиции заказа "));
        OrderPosition = new JTextField(10);
        p.add(OrderPosition);
        topPanel.add(p);



        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        p.add(btn);

        btn = new JButton("Cancel");
        btn.addActionListener(e -> {
            dispose();
        });
        p.add(btn);

        topPanel.add(p);

        getContentPane().add(topPanel, BorderLayout.CENTER);

        setResizable(false);
        pack();
    }

    public Date getLocalDate() {
        return new Date(localDate.getText());
    }

    public String getFioCustomer() {
        return fioCustomer.getText();
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

    public String getOrderStatus() {
        return (String) orderStatus.getModel().getSelectedItem();
    }

    public Date getDateSendOrder() {
        return new Date(dateSendOrder.getText());
    }

    public String getOrderPosition() {
        return OrderPosition.getText();
    }

    public boolean isModalResult() {
        return modalResult;
    }
}

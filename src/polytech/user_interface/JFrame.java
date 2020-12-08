package polytech.user_interface;

import polytech.source.controllers.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JFrame extends javax.swing.JFrame {

    private JLabel label1;
    private JLabel label2;
    private Container contentPane;
    private JButton button1;
    private JButton button2;
    private ProductController productController;

    public static void main(String[] args) {
        System.out.println("start");
        new JFrame();
        System.out.println("end");
    }

    private JFrame() {
        setWindowListeners();
        init();
        setVisible(true);
    }


    private void setWindowListeners() {
        Point p = new Point();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("width: " + d.width + ", height:" + d.height);
        setSize(640, 480);
        p.x = (d.width - 640) / 2;
        p.y = (d.height - 480) / 2;
        setLocation(p);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowListener());
    }


    private void init() {
        setTitle("Система учёта заказов и продуктов");
        setResizable(false);
        label1 = new JLabel("Заказы", JLabel.CENTER);
        label2 = new JLabel("Продукты", JLabel.CENTER);
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        contentPane = getContentPane();
        contentPane.add(label1);
        contentPane.add(label2);
        button1 = new JButton("Загрузка списка заказов");
        button2 = new JButton("Загрузка списка продуктов");
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        Point p1 = new Point(60, 90);
        Point p2 = new Point(60, 120);
        Dimension d = new Dimension(260, 32);
        label1.setSize(d);
        label2.setSize(d);
        label1.setLocation(p1);
        label2.setLocation(p2);
        button1.setSize(d);
        button2.setSize(d);
        p1.y = (int) p1.getY() + 10;
        p2.y = (int) p2.getY() + 30;
        button1.setLocation(p1);
        button2.setLocation(p2);

//        defaultColor = contentPane.getBackground();
//        newColor = Color.BLUE;
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productController.loadProducts();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
//                contentPane.setBackground(
//                        contentPane.getBackground() == defaultColor
//                                ? newColor : defaultColor);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                contentPane.setBackground(
//                        contentPane.getBackground() == defaultColor
//                                ? newColor : defaultColor);
            }
        });

        }


}
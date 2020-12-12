package polytech.source;


import polytech.user_interface.ui.MainFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}


//todo: добавить проверку на чтение продуктов что нет файла
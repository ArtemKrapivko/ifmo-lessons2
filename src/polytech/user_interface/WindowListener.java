package polytech.user_interface;

import java.awt.event.WindowEvent;

public class WindowListener implements java.awt.event.WindowListener{

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
        public void windowClosing(WindowEvent we) {
            System.out.println("Window closing");
            we.getWindow().dispose();
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


}

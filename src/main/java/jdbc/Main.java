package jdbc;

import jdbc.GUI.ChooseWindow;

import javax.swing.*;

public class Main {
    /**
     * @author Damian Mierzyński
     * @since 30-01-2020
     * @version 1.0
     */
    public static final String URL = "url";
    public static final String Login = "login";
    public static final String Password = "password";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChooseWindow::new);
    }
}

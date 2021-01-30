package jdbc;

import jdbc.GUI.ChooseWindow;

import javax.swing.*;

public class Main {
    /**
     * @author Damian Mierzyński
     * @since 30-01-2020
     * @version 1.0
     */
    public static final String URL = "jdbc:postgresql://localhost/rest";
    public static final String Login = "postgres";
    public static final String Password = "123";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChooseWindow::new);
    }
}

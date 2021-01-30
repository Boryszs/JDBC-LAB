package jdbc;

import jdbc.GUI.ChooseWindow;

import javax.swing.*;

public class Main {
    /**
     * @author Damian Mierzyński
     * @since 30-01-2020
     * @version 1.0
     */
    public static final String URL = "jdbc:postgresql://195.150.230.210:5434/2020_mierzynski_damian";
    public static final String Login = "2020_mierzynski_damian";
    public static final String Password = "32025";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChooseWindow::new);
    }
}

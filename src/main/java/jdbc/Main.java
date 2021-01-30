package jdbc;

import jdbc.GUI.AddPracownikGUI;
import jdbc.GUI.ChooseWindow;
import jdbc.model.Pracownik;

import javax.swing.*;

public class Main {
    public static final String URL = "jdbc:postgresql://localhost/rest";
    public static final String Login = "postgres";
    public static final String Password = "123";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChooseWindow::new);
    }
}

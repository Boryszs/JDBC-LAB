package jdbc;

import jdbc.GUI.ChooseWindow;

import javax.swing.*;

public class Main {
   public static final String URL = "jdbc:postgresql://localhost/rest";
   public static  final String Login = "postgres";
   public static  final String Password = "123";
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChooseWindow();
            }
        });
    }
}

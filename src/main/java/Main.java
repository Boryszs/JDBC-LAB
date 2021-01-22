import javax.swing.*;

public class Main {

    static final String URL = "jdbc:postgresql://localhost/rest";
    static  final String Login = "postgres";
    static  final String Password = "123";
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChooseWindow();
            }
        });
    }
}

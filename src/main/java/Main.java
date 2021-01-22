import javax.swing.*;

public class Main {

    static final String URL = "jdbc:postgresql://localhost/rest";
    static  final String Login = "postgres";
    static  final String Password = "123";
    public static void main(String[] args) {
        Klient klient = new Klient();
        klient.updateKlient();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //KlientGui klientGui = new KlientGui(klient.getAllKlients());
            }
        });

    }
}

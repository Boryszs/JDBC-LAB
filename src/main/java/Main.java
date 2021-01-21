import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String URL = "jdbc:postgresql://195.150.230.210:5434/2020_mierzynski_damian";
        String Login = "2020_mierzynski_damian";
        String Password = "32025";
        Connection connection = null;
        Statement statement = null;
        List<Klient> klients = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(URL, Login, Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM restauracja.klient;");
            while (resultSet.next()) {
                klients.add(new Klient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
            }
            resultSet.close();
            statement.close();
            connection.close();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new KlientGui(klients);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}

package jdbc.model;

import jdbc.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pracownik {

    private static final Logger logger = LoggerFactory.getLogger(Pracownik.class);

    private Integer idPracownika;
    private Double pensja;
    private String rola;
    private Integer idOsoby;

    public Pracownik() {
    }

    public Pracownik(Integer idPracownika, Double pensja, String rola, Integer idOsoby) {
        this.idPracownika = idPracownika;
        this.pensja = pensja;
        this.rola = rola;
        this.idOsoby = idOsoby;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public Double getPensja() {
        return pensja;
    }

    public String getRola() {
        return rola;
    }

    public Integer getIdOsoby() {
        return idOsoby;
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
    }

    public void setPensja(Double pensja) {
        this.pensja = pensja;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public void setIdOsoby(Integer idOsoby) {
        this.idOsoby = idOsoby;
    }

    public static List<Pracownik> getPracownik() {
        Connection connection = null;
        Statement statement = null;
        List<Pracownik> pracownicy = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM restauracja.pracownik ORDER BY id_pracownika;");
            logger.info("Execute Querry");
            while (resultSet.next()) {
                pracownicy.add(new Pracownik(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getString(3), resultSet.getInt(4)));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return pracownicy;
    }

    public void updatePensja() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.pracownik SET pensja = ? WHERE id_osoby = ? RETURNING pensja;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setDouble(1, this.pensja);
            statement.setInt(2, this.idOsoby);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            this.setPensja(resultSet.getDouble("pensja"));
            logger.info("Succes Update Pensja " + this.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateRola() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.pracownik SET rola = (? :: restauracja.\"Rola\") WHERE id_osoby = ? RETURNING rola;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();

            statement.setString(1, this.rola);
            statement.setInt(2, this.idOsoby);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("rola"));
            this.setRola(resultSet.getString("rola"));
            logger.info("Succes Update Rola " + this.toString());
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    @Override
    public String toString() {
        return "Pracownik{" +
                "idPracownika=" + idPracownika +
                ", pensja=" + pensja +
                ", rola='" + rola + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }
}

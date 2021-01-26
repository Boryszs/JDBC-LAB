package jdbc.model;

import jdbc.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Pracownik {

    private static final Logger logger = LoggerFactory.getLogger(Pracownik.class);

    private Integer idPracownika;
    private String pensja;
    private String rola;
    private Integer idOsoby;

    public Pracownik() {
    }

    public Pracownik(Integer idPracownika, String pensja, String rola, Integer idOsoby) {
        this.idPracownika = idPracownika;
        this.pensja = pensja;
        this.rola = rola;
        this.idOsoby = idOsoby;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public String getPensja() {
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

    public void setPensja(String pensja) {
        this.pensja = pensja;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public void setIdOsoby(Integer idOsoby) {
        this.idOsoby = idOsoby;
    }

    public static List<Pracownik> getPracownik(){
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

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            System.out.println(resultSetMetaData.getColumnType(1));
            System.out.println(resultSetMetaData.getColumnType(2));
            System.out.println(resultSetMetaData.getColumnType(3));
            System.out.println(resultSetMetaData.getColumnType(4));

            while (resultSet.next()) {
                pracownicy.add(new Pracownik(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
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

package jdbc.model;

import jdbc.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Adres {

    private static final Logger logger = LoggerFactory.getLogger(Klient.class);

    private int idAdresu;
    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String kodPocztowy;

    public Adres() {
    }

    public Adres(int idAdresu, String miejscowosc, String ulica, String nrDomu, String kodPocztowy) {
        this.idAdresu = idAdresu;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.kodPocztowy = kodPocztowy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public int getIdAdresu() {
        return idAdresu;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setIdAdresu(int idAdresu) {
        this.idAdresu = idAdresu;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public static List<Adres> getAdresy(){
        Connection connection = null;
        Statement statement = null;
        List<Adres> adresy = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM restauracja.adres;");
            logger.info("Execute Querry");
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println(metaData.getColumnType(1));
            System.out.println(metaData.getColumnType(2));
            System.out.println(metaData.getColumnType(3));
            System.out.println(metaData.getColumnType(4));
            System.out.println(metaData.getColumnType(5));


            while (resultSet.next()) {
                adresy.add(new Adres(resultSet.getInt("id_adresu"),resultSet.getString("miejscowosc"),resultSet.getString("ulica"),resultSet.getString("nr_domu"),resultSet.getString("kod_pocztowy")));
            }
            resultSet.close();
            statement.close();
            connection.close();

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
        return adresy;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "idAdresu=" + idAdresu +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nrDomu='" + nrDomu + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                '}';
    }
}

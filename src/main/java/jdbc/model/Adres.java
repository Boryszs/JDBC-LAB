package jdbc.model;

import jdbc.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Adres {

    private static final Logger logger = LoggerFactory.getLogger(Adres.class);

    private Integer idAdresu;
    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String kodPocztowy;

    public Adres() {
        this.idAdresu = null;
        this.miejscowosc = null;
        this.ulica = null;
        this.nrDomu = null;
        this.kodPocztowy = null;
    }

    public Adres(Integer idAdresu, String miejscowosc, String ulica, String nrDomu, String kodPocztowy) {
        this.idAdresu = idAdresu;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.kodPocztowy = kodPocztowy;
    }

    public Integer getIdAdresu() {
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

    public void setIdAdresu(Integer idAdresu) {
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

    public static List<Adres> getAdresy() {
        Connection connection = null;
        Statement statement = null;
        List<Adres> adresy = new LinkedList<>();
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM restauracja.adres ORDER BY id_adresu;");
            logger.info("Execute Querry");
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                adresy.add(new Adres(resultSet.getInt("id_adresu"), resultSet.getString("miejscowosc"), resultSet.getString("ulica"), resultSet.getString("nr_domu"), resultSet.getString("kod_pocztowy")));
            }
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
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return adresy;
    }

    public void updateMiejscowosc() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.adres SET miejscowosc = ? WHERE id_adresu = ? RETURNING miejscowosc;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.miejscowosc);
            statement.setInt(2, this.idAdresu);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setMiejscowosc(resultSet.getString("miejscowosc"));
            logger.info("Succes Update Miejscowosc " + this.toString());
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
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateUlica() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.adres SET ulica = ? WHERE id_adresu = ? RETURNING ulica;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.ulica);
            statement.setInt(2, this.idAdresu);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setUlica(resultSet.getString("ulica"));
            logger.info("Succes Update Ulica " + this.toString());
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
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateNrDomu() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.adres SET nr_domu = ? WHERE id_adresu = ? RETURNING nr_domu;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.nrDomu);
            statement.setInt(2, this.idAdresu);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setUlica(resultSet.getString("nr_domu"));
            logger.info("Succes Update Nr Domu " + this.toString());
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
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateKodPocztowy() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.adres SET kod_pocztowy = ? WHERE id_adresu = ? RETURNING kod_pocztowy;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.kodPocztowy);
            statement.setInt(2, this.idAdresu);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setUlica(resultSet.getString("kod_pocztowy"));
            logger.info("Succes Update Nr Domu " + this.toString());
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
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Integer insertAdres(Adres adres) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        Integer result = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            String sql = "INSERT INTO restauracja.adres (miejscowosc,ulica,nr_domu,kod_pocztowy) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, adres.getMiejscowosc());
            statement.setString(2, adres.getUlica());
            statement.setString(3, adres.getNrDomu());
            statement.setString(4, adres.getKodPocztowy());
            result = statement.executeUpdate();
            if (result > 0) {
                logger.info("Succes Insert adres " + adres.toString());
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                System.out.println();
                return resultSet.getInt(1);
            }
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
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
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

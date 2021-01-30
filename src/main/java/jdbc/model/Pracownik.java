package jdbc.model;

import jdbc.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pracownik {

    private static final Logger logger = LoggerFactory.getLogger(Pracownik.class);

    private Integer idPracownika;
    private Double pensja;
    private String rola;
    private Integer idOsoby;

    public Pracownik() {
    }

    public Pracownik(Double pensja, String rola, Integer idOsoby) {
        this.pensja = pensja;
        this.rola = rola;
        this.idOsoby = idOsoby;
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
        ResultSet resultSet = null;
        List<Pracownik> pracownicy = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM restauracja.pracownik ORDER BY id_pracownika;");
            logger.info("Execute Querry");
            while (resultSet.next()) {
                pracownicy.add(new Pracownik(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getString(3), resultSet.getInt(4)));
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
        return pracownicy;
    }

    // ROLLBACK COMMIT
    public Integer updatePensja() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer updateValue = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String sql = "UPDATE restauracja.pracownik SET pensja = ? WHERE id_osoby = ? RETURNING pensja;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setDouble(1, this.pensja);
            statement.setInt(2, this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.beforeFirst();
            if (resultSet.next()) {
                this.setPensja(resultSet.getDouble("pensja"));
                if (this.pensja > 10000) {
                    logger.error("ERROR violation update pensja");
                    TimeUnit.SECONDS.sleep(5);
                    connection.rollback();
                    updateValue = -1;
                } else {
                    TimeUnit.SECONDS.sleep(5);
                    connection.commit();
                    logger.info("Succes Update Pensja " + this.toString());
                    updateValue = 1;
                }
            } else {
                logger.error("Not data update");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                try {
                    TimeUnit.SECONDS.sleep(15);
                    connection.rollback();
                    System.out.println(e.getMessage());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return updateValue;
    }

    public void updateRola() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.pracownik SET rola = (? :: restauracja.\"Rola\") WHERE id_osoby = ? RETURNING rola;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();

            statement.setString(1, this.rola);
            statement.setInt(2, this.idOsoby);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setRola(resultSet.getString("rola"));
            logger.info("Succes Update Rola " + this.toString());
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

    public static Integer insertPracownik(Pracownik pracownik) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        Integer result = null;
        Integer id = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            String sql = "INSERT INTO restauracja.pracownik (pensja,rola,id_osoby) VALUES (?,(? :: restauracja.\"Rola\"),?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1,pracownik.getPensja());
            statement.setString(2,pracownik.getRola());
            statement.setInt(3,pracownik.getIdOsoby());
            result = statement.executeUpdate();
            if (result > 0) {
                logger.info("Succes Insert pracownik " + pracownik.toString());
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                id = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
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
            return id;
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

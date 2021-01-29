package jdbc.model;

import jdbc.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Klient {

    private static final Logger logger = LoggerFactory.getLogger(Klient.class);

    private Integer idKlient;
    private String login;
    private String haslo;
    private Integer idOsoby;

    public Klient() {
    }

    public Klient(Integer idKlient, String login, String haslo, Integer idOsoby) {
        this.idKlient = idKlient;
        this.login = login;
        this.haslo = haslo;
        this.idOsoby = idOsoby;
    }

    public Integer getIdKlient() {
        return idKlient;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public Integer getIdOsoby() {
        return idOsoby;
    }

    public void setIdKlient(Integer idKlient) {
        this.idKlient = idKlient;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setIdOsoby(Integer idOsoby) {
        this.idOsoby = idOsoby;
    }

    public void getKlientLogin() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT login FROM restauracja.klient WHERE id_klienta = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1, this.idKlient);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setLogin(resultSet.getString("login"));
            logger.info("Refresh Login " + this.toString());
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

    public void getKlientHaslo() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT haslo FROM restauracja.klient WHERE id_klienta = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1, this.idKlient);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setHaslo(resultSet.getString("haslo"));
            logger.info("Refresh Haslo " + this.toString());
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

    public static List<Klient> getKlient() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Klient> klienci = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM restauracja.klient ORDER BY id_klienta;");
            logger.info("Execute Querry");
            while (resultSet.next()) {
                klienci.add(new Klient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
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
        return klienci;
    }

    public static void addKlient(String login, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "INSERT INTO  restauracja.klient (login,haslo,id_osoby) VALUES(?, crypt(?, gen_salt('md5')),10);";
            statement = connection.prepareStatement(sql);
            statement.clearParameters();
            statement.setString(1, login);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Succes Insert jdbc.model.Klient");
            } else {
                logger.info("Not Insert");
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateLogin() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.klient SET login = ? WHERE id_klienta = ? RETURNING login;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.login);
            statement.setInt(2, this.idKlient);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setLogin(resultSet.getString("login"));
            logger.info("Update Login Successfull " + this.toString());
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

    public void updateHaslo() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.klient SET haslo = crypt(?, gen_salt('md5')) WHERE id_klienta = ? RETURNING haslo;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.haslo);
            statement.setInt(2, this.idKlient);

            resultSet = statement.executeQuery();
            resultSet.next();
            this.setHaslo(resultSet.getString("haslo"));
            logger.info("Update Haslo Successfull " + this.toString());
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

    public void updateKlient() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.klient SET login = ?, haslo =  crypt(?, gen_salt('md5')) WHERE id_klienta = ?;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1, this.login);
            statement.setString(2, this.haslo);
            statement.setInt(3, this.idKlient);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Succes Update");
            } else {
                logger.info("Not Update");
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Integer insertKlient(Klient klient) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        Integer result = null;
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            connection.setAutoCommit(false);
            logger.info("Connecting succesfull");
            String sql = "INSERT INTO restauracja.klient (login,haslo,id_osoby) VALUES (?,crypt(?, gen_salt('md5')),?)";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, klient.getLogin());
            statement.setString(2, klient.getHaslo());
            statement.setInt(3, klient.getIdOsoby());
            result = statement.executeUpdate();
            connection.commit();
            if (result > 0) {
                logger.info("Succes Insert klient " + klient.toString());
                resultSet.next();
                System.out.println(resultSet.getInt(1));
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Rollback");
            //throwables.printStackTrace();
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
        return "jdbc.model.Klient{" +
                "idKlient=" + idKlient +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }

}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Klient {

    private static final Logger logger = LoggerFactory.getLogger(Klient.class);

    private int idKlient;
    private String login;
    private String haslo;
    private int idOsoby;

    public Klient() {
    }

    public Klient(int idKlient, String login, String haslo, int idOsoby) {
        this.idKlient = idKlient;
        this.login = login;
        this.haslo = haslo;
        this.idOsoby = idOsoby;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public int getIdOsoby() {
        return idOsoby;
    }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setIdOsoby(int idOsoby) {
        this.idOsoby = idOsoby;
    }

    public List<Klient> getAllKlients(){
        Connection connection = null;
        Statement statement = null;
        List<Klient> klients = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM restauracja.klient;");
            logger.info("Execute Querry");
            while (resultSet.next()) {
                klients.add(new Klient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
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
        return klients;
    }

    public void updateKlient(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.klient SET Login =  crypt(?, gen_salt('md5')) WHERE id_klienta = ?;";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1,"DAsw2#$5S2");
            statement.setInt(2,14);

            int rowsInserted  = statement.executeUpdate();
            System.out.println("OK");
            if(rowsInserted>0){
                System.out.println("Succes Update");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
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
        return "Klient{" +
                "idKlient=" + idKlient +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }

}

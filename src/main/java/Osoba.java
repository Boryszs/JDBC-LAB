import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Osoba {
   private static final Logger logger = LoggerFactory.getLogger(Osoba.class);

   private int idOsoby;
   private String imie;
   private String nazwisko;
   private String pesel;
   private Date dataUrodzenia;
   private String email;
   private String telefon;
   private int idAdresu;

    public Osoba() {
    }

    public Osoba(int idOsoby, String imie, String nazwisko, String pesel, Date dataUrodzenia, String email, String telefon, int idAdresu) {
        this.idOsoby = idOsoby;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.email = email;
        this.telefon = telefon;
        this.idAdresu = idAdresu;
    }

    public int getIdOsoby() {
        return idOsoby;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getIdAdresu() {
        return idAdresu;
    }

    public void setIdOsoby(int idOsoby) {
        this.idOsoby = idOsoby;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setIdAdresu(int idAdresu) {
        this.idAdresu = idAdresu;
    }

    public static List<Osoba> getOsoby() {
        Connection connection = null;
        Statement statement = null;
        List<Osoba> osoby = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(Main.URL, Main.Login, Main.Password);
            logger.info("Connecting succesfull");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM restauracja.osoba ORDER BY id_osoby;");
            logger.info("Execute Querry");
            while (resultSet.next()) {
                osoby.add(new Osoba(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8)));
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
        return osoby;
    }

    public void selectImie(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT imie FROM restauracja.osoba WHERE id_osoby = ?";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1,this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setImie(resultSet.getString("imie"));
            logger.info("Refresh Imie "+this.toString());
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

    public void selectNazwisko(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT nazwisko FROM restauracja.osoba WHERE id_osoby = ?";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1,this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setNazwisko(resultSet.getString("nazwisko"));
            logger.info("Refresh Nazwisko "+this.toString());
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

    public void selectPesel(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT pesel FROM restauracja.osoba WHERE id_osoby = ?";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1,this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setPesel(resultSet.getString("pesel"));
            logger.info("Refresh Pesel "+this.toString());
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

    public void selectDataUrodzenia(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT data_urodzania FROM restauracja.osoba WHERE id_osoby = ?";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1,this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setDataUrodzenia(resultSet.getDate("data_urodzenia"));
            logger.info("Refresh Data Urodzenia "+this.toString());
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

    public void selectEmail(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT email FROM restauracja.osoba WHERE id_osoby = ?";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1,this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setEmail(resultSet.getString("email"));
            logger.info("Refresh Email "+this.toString());
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

    public void selectTelefon(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "SELECT telefon FROM restauracja.osoba WHERE id_osoby = ?";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setInt(1,this.idOsoby);
            resultSet = statement.executeQuery();
            resultSet.next();
            this.setTelefon(resultSet.getString("telefon"));
            logger.info("Refresh Telefon "+this.toString());
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

    public void updateImie(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.osoba SET imie = ? WHERE id_osoby = ?;";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1,this.imie);
            statement.setInt(2,this.idOsoby);

            int rowsInserted  = statement.executeUpdate();
            if(rowsInserted>0){
                logger.info("Succes Update Imie");
            }else{
                logger.info("Not Update");
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

    public void updateNazwisko(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.osoba SET nazwisko = ? WHERE id_osoby = ?;";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1,this.nazwisko);
            statement.setInt(2,this.idOsoby);

            int rowsInserted  = statement.executeUpdate();
            if(rowsInserted>0){
                logger.info("Succes Update Nazwisko");
            }else{
                logger.info("Not Update");
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

    public void updatePesel(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.osoba SET pesel = ? WHERE id_osoby = ?;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1,this.pesel);
            statement.setInt(2,this.idOsoby);

            int rowsInserted  = statement.executeUpdate();
            if(rowsInserted>0){
                logger.info("Succes Update Data Urodzenia");
            }else{
                logger.info("Not Update");
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

    public void updateDataUrodzenia(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.osoba SET data_urodzenia = ? WHERE id_osoby = ?;";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setDate(1, new java.sql.Date(this.dataUrodzenia.getTime()));
            statement.setInt(2,this.idOsoby);

            int rowsInserted  = statement.executeUpdate();
            if(rowsInserted>0){
                logger.info("Succes Update Data Urodzenia");
            }else{
                logger.info("Not Update");
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

    public void updateEmail(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.osoba SET email = ? WHERE id_osoby = ?;";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1,this.email);
            statement.setInt(2,this.idOsoby);

            int rowsInserted  = statement.executeUpdate();
            if(rowsInserted>0){
                logger.info("Succes Update Email");
            }else{
                logger.info("Not Update");
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

    public void updateTelefon(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(Main.URL,Main.Login,Main.Password);
            logger.info("Connecting succesfull");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            String sql = "UPDATE restauracja.osoba SET telefon = ? WHERE id_osoby = ?;";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.clearParameters();
            statement.setString(1,this.telefon);
            statement.setInt(2,this.idOsoby);

            int rowsInserted  = statement.executeUpdate();
            if(rowsInserted>0){
                logger.info("Succes Update Telefon");
            }else{
                logger.info("Not Update");
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
        return "Osoba{" +
                "idOsoby=" + idOsoby +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", idAdresu=" + idAdresu +
                '}';
    }
}

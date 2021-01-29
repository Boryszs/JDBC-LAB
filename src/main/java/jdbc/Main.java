package jdbc;

import jdbc.GUI.ChooseWindow;
import jdbc.model.Adres;
import jdbc.model.Klient;
import jdbc.model.Osoba;

import javax.swing.*;
import java.util.Date;

public class Main {
   public static final String URL = "jdbc:postgresql://localhost/rest";
   public static  final String Login = "postgres";
   public static  final String Password = "123";
    public static void main(String[] args) {
        //Adres.insertAdres(new Adres());
        //Osoba.insertOsoba(new Osoba(2,"Damian","Mierzyński","99999123451",new java.util.Date(2000,01,01),"123@gmail.com","(0)752137915",3004));
        //Klient.insertKlient(new Klient(2,"d7650m","1234567",3004));
        SwingUtilities.invokeLater(ChooseWindow::new);
    }
}

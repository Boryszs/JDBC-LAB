package jdbc.GUI;

import jdbc.model.Adres;
import jdbc.model.Klient;
import jdbc.model.Osoba;
import jdbc.model.Pracownik;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class AddPracownikGUI extends JFrame {

    private TextField miejscowoscText;
    private TextField ulicaText;
    private TextField nrDomuText;
    private TextField kodPocztowyText;
    private Label miejscowoscLabel;
    private Label ulicaLabel;
    private Label nrDomuLabel;
    private Label kodPocztowyLabel;

    private TextField imieText;
    private TextField nazwiskoText;
    private TextField peselText;
    private TextField dataUrodzeniaText;
    private TextField emailText;
    private TextField telefonText;
    private Label imieLabel;
    private Label nazwiskoLabel;
    private Label peselLabel;
    private Label dataUrodzeniaLabel;
    private Label emailLabel;
    private Label telefonLabel;


    private Label pensjaLabel;
    private Label rolaLabel;
    private TextField pensjaText;
    private JComboBox comboBox;


    private JPanel mainPanel;
    private Border daneB;

    private JButton addButon;
    private JButton backButton;

    public AddPracownikGUI() {

        daneB = BorderFactory.createTitledBorder("Dane");
        mainPanel = new JPanel(new GridLayout(13, 1));

        miejscowoscLabel = new Label("Miejscowosc:");
        ulicaLabel = new Label("Ulica:");
        nrDomuLabel = new Label("Nr Domu:");
        kodPocztowyLabel = new Label("Kod Pocztowy:");

        miejscowoscText = new TextField();
        miejscowoscText.setPreferredSize(new Dimension(160, 20));
        ulicaText = new TextField();
        ulicaText.setPreferredSize(new Dimension(160, 20));
        nrDomuText = new TextField();
        nrDomuText.setPreferredSize(new Dimension(160, 20));
        kodPocztowyText = new TextField();
        kodPocztowyText.setPreferredSize(new Dimension(160, 20));

        mainPanel.add(miejscowoscLabel);
        mainPanel.add(miejscowoscText);


        mainPanel.add(ulicaLabel);
        mainPanel.add(ulicaText);

        mainPanel.add(nrDomuLabel);
        mainPanel.add(nrDomuText);

        mainPanel.add(kodPocztowyLabel);
        mainPanel.add(kodPocztowyText);


        imieLabel = new Label("Imie:");
        nazwiskoLabel = new Label("Nazwisko:");
        peselLabel = new Label("Pesel:");
        dataUrodzeniaLabel = new Label("Data Urodzenia:");
        emailLabel = new Label("Email:");
        telefonLabel = new Label("Telefon:");

        imieText = new TextField();
        imieText.setPreferredSize(new Dimension(160, 20));
        nazwiskoText = new TextField();
        nazwiskoText.setPreferredSize(new Dimension(160, 20));
        peselText = new TextField();
        peselText.setPreferredSize(new Dimension(160, 20));
        dataUrodzeniaText = new TextField();
        dataUrodzeniaText.setPreferredSize(new Dimension(160, 20));
        emailText = new TextField();
        emailText.setPreferredSize(new Dimension(160, 20));
        telefonText = new TextField();
        telefonText.setPreferredSize(new Dimension(160, 20));

        mainPanel.add(imieLabel);
        mainPanel.add(imieText);

        mainPanel.add(nazwiskoLabel);
        mainPanel.add(nazwiskoText);

        mainPanel.add(peselLabel);
        mainPanel.add(peselText);

        mainPanel.add(dataUrodzeniaLabel);
        mainPanel.add(dataUrodzeniaText);

        mainPanel.add(emailLabel);
        mainPanel.add(emailText);

        mainPanel.add(telefonLabel);
        mainPanel.add(telefonText);


        pensjaLabel = new Label("Pensja:");
        pensjaText = new TextField();

        rolaLabel = new Label("Rola:");
        comboBox = new JComboBox();
        comboBox.addItem("kucharz");
        comboBox.addItem("kelner");
        comboBox.addItem("sprzedawca");

        mainPanel.add(pensjaLabel);
        mainPanel.add(pensjaText);

        mainPanel.add(rolaLabel);
        mainPanel.add(comboBox);

        addButon = new JButton("Dodaj");
        backButton = new JButton("<-");

        mainPanel.add(backButton);
        mainPanel.add(addButon);

        mainPanel.setBorder(daneB);

        this.add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setPreferredSize(new Dimension(200, 200));
        this.setTitle("Adresy klientów z bazy Restauracja");
        this.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(ChooseWindow::new);
                dispose();
            }
        });

        addButon.addActionListener(e -> {

            if (emailText.getText().matches("([a-zA-Z0-9-+]+@([a-zA-Z0-9-+])+.(com|org|edu|nz|au))") && peselText.getText().length() == 11 && telefonText.getText().matches("^([(0)])+([0-9]){9,}$") && Double.parseDouble(pensjaText.getText()) > 0 && Double.parseDouble(pensjaText.getText()) < 10000) {
                try {
                    int idAdresu = new Adres().insertAdres(new Adres(miejscowoscText.getText(), ulicaText.getText(), nrDomuText.getText(), kodPocztowyText.getText()));
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    TimeUnit.SECONDS.sleep(5);
                    Osoba osoba = new Osoba(imieText.getText(), nazwiskoText.getText(), peselText.getText(), new SimpleDateFormat("dd-MM-yyyy").parse(dataUrodzeniaText.getText()), emailText.getText(), telefonText.getText(), idAdresu);
                    Integer idOsoby = Osoba.insertOsoba(osoba);
                    TimeUnit.SECONDS.sleep(5);
                    Pracownik pracownik = new Pracownik(Double.parseDouble(pensjaText.getText()), (String) comboBox.getSelectedItem(), idOsoby);
                    Pracownik.insertPracownik(pracownik);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

        });

    }
}


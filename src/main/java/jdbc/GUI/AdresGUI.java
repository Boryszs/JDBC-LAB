package jdbc.GUI;

import jdbc.GUI.model.AdresTableModel;
import jdbc.model.Adres;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdresGUI extends JFrame {

    private List<Adres> adresyList;
    private FileWriter writer;
    private StringBuilder dataAdresy;
    private AdresTableModel adresTableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private JButton csvButton;
    private JPanel mainPanel;
    private JPanel panelB;
    private JPanel tablePanel;

    public AdresGUI() {
        this.adresyList = Adres.getAdresy();
        adresTableModel = new AdresTableModel(adresyList);
        table = new JTable(adresTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850, 600));
        scrollPane = new JScrollPane(table);

        buttonBack = new JButton("<-");
        csvButton = new JButton("csv");


        mainPanel = new JPanel();
        panelB = new JPanel();

        panelB.add(buttonBack);
        panelB.add(csvButton);
        panelB.setAlignmentX(Component.CENTER_ALIGNMENT);

        tablePanel = new JPanel();
        tablePanel.add(scrollPane);

        mainPanel.add(panelB);
        mainPanel.add(tablePanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Adresy klientów z bazy Restauracja");
        this.setVisible(true);


        csvButton.addActionListener(e -> {
            if (adresyList.size() != 0) {
                try {
                    writer = new FileWriter(new File("adresy.csv"));
                    dataAdresy = new StringBuilder();
                    dataAdresy.append("Id");
                    dataAdresy.append(',');
                    dataAdresy.append("Miejscowosc");
                    dataAdresy.append(',');
                    dataAdresy.append("ulica");
                    dataAdresy.append(',');
                    dataAdresy.append("Nr domu");
                    dataAdresy.append(',');
                    dataAdresy.append("Kod pocztowy");
                    dataAdresy.append('\n');
                    for (Adres adres : adresyList) {
                        dataAdresy.append(adres.getIdAdresu());
                        dataAdresy.append(',');
                        dataAdresy.append(adres.getMiejscowosc());
                        dataAdresy.append(',');
                        dataAdresy.append(adres.getUlica());
                        dataAdresy.append(',');
                        dataAdresy.append(adres.getNrDomu());
                        dataAdresy.append(',');
                        dataAdresy.append(adres.getKodPocztowy());
                        dataAdresy.append('\n');
                    }

                    writer.write(dataAdresy.toString());
                    writer.flush();
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        buttonBack.addActionListener(e -> {
            SwingUtilities.invokeLater(ChooseWindow::new);
            dispose();
        });
    }
}

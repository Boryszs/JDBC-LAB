package jdbc.GUI;

import jdbc.GUI.model.OsobaTableModel;
import jdbc.model.Osoba;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OsobaGUI extends JFrame {

    private List<Osoba> osobaList;
    private OsobaTableModel osobaTableModel;
    private FileWriter writer;
    private StringBuilder dataOsoby;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private JButton csvButton;
    private JPanel panelB;
    private JPanel mainPanel;
    private JPanel panelT;

    public OsobaGUI() {
        osobaList = Osoba.getOsoby();
        osobaTableModel = new OsobaTableModel(osobaList);

        table = new JTable(osobaTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1150, 600));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(table);

        buttonBack = new JButton("<-");
        csvButton = new JButton("csv");

        panelB = new JPanel();
        panelB.add(buttonBack);
        panelB.add(csvButton);
        panelB.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel = new JPanel();
        panelT = new JPanel();
        panelT.add(scrollPane);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelB);
        mainPanel.add(panelT);


        this.add(mainPanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Dane Osób z bazy Restauracja");
        this.setVisible(true);

        csvButton.addActionListener(e -> {
            if (osobaList.size() != 0) {
                try {
                    writer = new FileWriter(new File("osoby.csv"));
                    dataOsoby = new StringBuilder();
                    dataOsoby.append("Id");
                    dataOsoby.append(',');
                    dataOsoby.append("Imie");
                    dataOsoby.append(',');
                    dataOsoby.append("Nazwisko");
                    dataOsoby.append(',');
                    dataOsoby.append("Pesel");
                    dataOsoby.append(',');
                    dataOsoby.append("Data urodzenia");
                    dataOsoby.append(',');
                    dataOsoby.append("Email");
                    dataOsoby.append(',');
                    dataOsoby.append("Telefon");
                    dataOsoby.append(',');
                    dataOsoby.append("Id adresu");
                    dataOsoby.append('\n');
                    for (Osoba osoba : osobaList) {
                        dataOsoby.append(osoba.getIdOsoby());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getImie());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getNazwisko());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getPesel());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getDataUrodzenia());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getEmail());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getTelefon());
                        dataOsoby.append(',');
                        dataOsoby.append(osoba.getIdAdresu());
                        dataOsoby.append('\n');
                    }

                    writer.write(dataOsoby.toString());
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

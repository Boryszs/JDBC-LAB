package jdbc.GUI;

import jdbc.GUI.model.KlientTableModel;
import jdbc.model.Klient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class KlientGui extends JFrame {

    private List<Klient> klientList;
    private FileWriter writer;
    private StringBuilder dataKlient;
    private KlientTableModel klientTableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private JButton csvButton;
    private JButton addButton;
    private JPanel panelB;
    private JPanel mainPanel;
    private JPanel panelT;

    public KlientGui() {

        klientList = Klient.getKlient();
        klientTableModel = new KlientTableModel(klientList);

        table = new JTable(klientTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850, 600));
        scrollPane = new JScrollPane(table);

        buttonBack = new JButton("<-");
        csvButton = new JButton("csv");
        addButton = new JButton("Stworz Klienta");

        panelB = new JPanel();
        mainPanel = new JPanel();
        panelT = new JPanel();

        panelB.add(buttonBack);
        panelB.add(csvButton);
        panelB.add(addButton);
        panelB.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelT.add(scrollPane);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelB);
        mainPanel.add(panelT);

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Klienci z bazy Restauracja");
        this.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(AddKlientGUI::new);
            }
        });
        csvButton.addActionListener(e -> {
            if (klientList.size() != 0) {
                try {
                    writer = new FileWriter(new File("klienci.csv"));
                    dataKlient = new StringBuilder();
                    dataKlient.append("Id");
                    dataKlient.append(',');
                    dataKlient.append("Imie");
                    dataKlient.append(',');
                    dataKlient.append("Haslo");
                    dataKlient.append(',');
                    dataKlient.append("Id osoby");
                    dataKlient.append('\n');
                    for (Klient klient : klientList) {
                        dataKlient.append(klient.getIdKlient());
                        dataKlient.append(',');
                        dataKlient.append(klient.getLogin());
                        dataKlient.append(',');
                        dataKlient.append(klient.getHaslo());
                        dataKlient.append(',');
                        dataKlient.append(klient.getIdOsoby());
                        dataKlient.append('\n');
                    }

                    writer.write(dataKlient.toString());
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

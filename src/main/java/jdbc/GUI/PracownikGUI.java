package jdbc.GUI;

import jdbc.GUI.model.PracownikTableModel;
import jdbc.model.Pracownik;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PracownikGUI extends JFrame {

    private List<Pracownik> pracownikList;
    private FileWriter writer;
    private StringBuilder dataPracownik;
    private PracownikTableModel pracownikTableModel;
    private JTable table;
    private TableColumn rola;
    private JComboBox comboBox;
    private JScrollPane scrollPane;
    private  JButton buttonBack;
    private JButton addButton;
    private  JButton csvButton;
    private JPanel panelB;
    private JPanel mainPanel;
    private JPanel panelT;

    public PracownikGUI(){
        pracownikList = Pracownik.getPracownik();
        pracownikTableModel = new PracownikTableModel(pracownikList);
        table = new JTable(pracownikTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(700,600));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        rola = table.getColumnModel().getColumn(2);
        comboBox = new JComboBox();
        comboBox.addItem("kucharz");
        comboBox.addItem("kelner");
        comboBox.addItem("sprzedawca");
        rola.setCellEditor(new DefaultCellEditor(comboBox));
        scrollPane = new JScrollPane(table);

        buttonBack = new JButton("<-");
        csvButton = new JButton("csv");
        addButton = new JButton("Stworz Pracownika");

        panelB = new JPanel();
        panelB.add(buttonBack);
        panelB.add(csvButton);
        panelB.add(addButton);
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
        this.setTitle("Dane Pracowników z bazy Restauracja");
        this.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(AddPracownikGUI::new);
            }
        });
        csvButton.addActionListener(e -> {
            if (pracownikList.size() != 0) {
                try {
                    writer = new FileWriter(new File("pracownicy.csv"));
                    dataPracownik = new StringBuilder();
                    dataPracownik.append("Id");
                    dataPracownik.append(',');
                    dataPracownik.append("Pensja");
                    dataPracownik.append(',');
                    dataPracownik.append("Rola");
                    dataPracownik.append(',');
                    dataPracownik.append("Id osoby");
                    dataPracownik.append('\n');
                    for (Pracownik pracownik : pracownikList) {
                        dataPracownik.append(pracownik.getIdPracownika());
                        dataPracownik.append(',');
                        dataPracownik.append(2);
                        dataPracownik.append(',');
                        dataPracownik.append(pracownik.getRola());
                        dataPracownik.append(',');
                        dataPracownik.append(pracownik.getIdOsoby());
                        dataPracownik.append('\n');
                    }
                    writer.write(dataPracownik.toString());
                    writer.flush();
                    writer.close();

                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            }
        });
        buttonBack.addActionListener(e -> {
            SwingUtilities.invokeLater(ChooseWindow::new);
            dispose();
        });
    }
}

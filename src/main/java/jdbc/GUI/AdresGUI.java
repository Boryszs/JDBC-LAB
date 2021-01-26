package jdbc.GUI;

import jdbc.GUI.Model.AdresTableModel;
import jdbc.model.Adres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdresGUI extends JFrame {

    private List<Adres> adresy;

    public AdresGUI() {
        this.adresy = Adres.getAdresy();
        AdresTableModel adresTableModel = new AdresTableModel(adresy);
        JTable table = new JTable(adresTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850, 600));
        JScrollPane scrollPane = new JScrollPane(table);
        JButton buttonBack = new JButton("<-");
        buttonBack.setAlignmentX(Component.LEFT_ALIGNMENT);

        Label lMiejscowsc = new Label("Miejscowsc:");
        TextField tMiejscowsc = new TextField();
        tMiejscowsc.setPreferredSize(new Dimension(100, 20));
        Label lUlica = new Label("Ulica:");
        TextField tUlica = new TextField();
        tUlica.setPreferredSize(new Dimension(100, 20));
        Label lNrDomu = new Label("Nr Domu:");
        TextField tNrDomu = new TextField();
        tNrDomu.setPreferredSize(new Dimension(100, 20));
        Label lKodPocztowy = new Label("Kod Pocztowy:");
        TextField tKodPocztowy = new TextField();
        tKodPocztowy.setPreferredSize(new Dimension(100, 20));
        JButton buttonInsert = new JButton("Insert");
        buttonInsert.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel mainPanel = new JPanel();
        JPanel panelB = new JPanel();
        JPanel panelBs = new JPanel();
        panelBs.add(buttonInsert,BorderLayout.CENTER);
        panelB.setLayout(new BorderLayout());
        panelB.add(buttonBack, BorderLayout.WEST);
        JPanel panelT = new JPanel();
        panelT.add(lMiejscowsc);
        panelT.add(tMiejscowsc);
        panelT.add(lUlica);
        panelT.add(tUlica);
        panelT.add(lNrDomu);
        panelT.add(tNrDomu);
        panelT.add(lKodPocztowy);
        panelT.add(tKodPocztowy);
        JPanel tablePanel = new JPanel();
        tablePanel.add(scrollPane);
        mainPanel.add(panelB);
        mainPanel.add(panelT);
        mainPanel.add(panelBs);
        mainPanel.add(tablePanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Adresy klientów z bazy Restauracja");
        this.setVisible(true);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new ChooseWindow();
                    }
                });
                dispose();
            }
        });
    }
}

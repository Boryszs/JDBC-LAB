package jdbc.GUI;

import jdbc.GUI.Model.AdresTableModel;
import jdbc.model.Adres;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdresGUI extends JFrame {

    private List<Adres> adresy;

    public AdresGUI(){
        this.adresy = Adres.getAdresy();
        AdresTableModel adresTableModel = new AdresTableModel(adresy);
        JTable table = new JTable(adresTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850,600));
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel mainPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        tablePanel.add(scrollPane);
        mainPanel.add(tablePanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Adresy klientów z bazy Restauracja");
        this.setVisible(true);
    }
}

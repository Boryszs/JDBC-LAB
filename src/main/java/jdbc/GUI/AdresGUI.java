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

    public AdresGUI(){
        this.adresy = Adres.getAdresy();
        AdresTableModel adresTableModel = new AdresTableModel(adresy);
        JTable table = new JTable(adresTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850,600));
        JScrollPane scrollPane = new JScrollPane(table);
        JButton buttonBack = new JButton("<-");
        buttonBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel mainPanel = new JPanel();
        JPanel panelB = new JPanel();
        panelB.setLayout(new BorderLayout());
        panelB.add(buttonBack,BorderLayout.WEST);
        JPanel tablePanel = new JPanel();
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

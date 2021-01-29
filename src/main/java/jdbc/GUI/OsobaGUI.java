package jdbc.GUI;

import jdbc.GUI.Model.OsobaTableModel;
import jdbc.model.Osoba;

import javax.swing.*;
import java.awt.*;

public class OsobaGUI extends JFrame{

    public OsobaGUI() {
            OsobaTableModel osobaTableModel = new OsobaTableModel(Osoba.getOsoby());
            JTable table = new JTable(osobaTableModel);
            table.setPreferredScrollableViewportSize(new Dimension(1150,600));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane scrollPane = new JScrollPane(table);
            JButton buttonBack = new JButton("<-");
            buttonBack.setAlignmentX(Component.LEFT_ALIGNMENT);
            JPanel panelB = new JPanel();
            panelB.setLayout(new BorderLayout());
            panelB.add(buttonBack,BorderLayout.WEST);
            JPanel mainPanel = new JPanel();
            JPanel panelT = new JPanel();
            panelT.add(scrollPane);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(panelB);
            mainPanel.add(panelT);

            buttonBack.addActionListener(e -> {
                    SwingUtilities.invokeLater(ChooseWindow::new);
                    dispose();
            });

            this.add(mainPanel);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setTitle("Dane Osób z bazy Restauracja");
            this.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            this.add(mainPanel);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setTitle("Dane Osób z bazy Restauracja");
            this.setVisible(true);
    }
}

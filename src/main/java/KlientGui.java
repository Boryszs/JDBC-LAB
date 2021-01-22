import javax.swing.*;
import java.awt.*;

public class KlientGui extends  JFrame{
    public KlientGui() {
        KlientTableModel klientTableModel = new KlientTableModel(Klient.getKlient());
        JTable table = new JTable(klientTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850,600));
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel mainPanel = new JPanel();
        JPanel panelT = new JPanel();
        panelT.add(scrollPane);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelT);
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Klienci z bazy Restauracja");
        this.setVisible(true);
    }
}

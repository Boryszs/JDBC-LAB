import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KlientGui extends  JFrame{
    public KlientGui(List<Klient> klientList) {
        KlientTableModel klientTableModel = new KlientTableModel(klientList);
        JTable table = new JTable(klientTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel mainPanel = new JPanel();
        JPanel panelT = new JPanel();
        panelT.add(scrollPane);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelT);
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Klienci z bazy Restauracja");
        this.setVisible(true);
    }
}

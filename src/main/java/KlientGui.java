import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KlientGui extends  JFrame{
    public KlientGui(List<Klient> klientList) {
        KlientTableModel klientTableModel = new KlientTableModel(klientList);
        JTable table = new JTable(klientTableModel);
        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KlientGui extends  JFrame{
    private List<Klient> klientList;
    public KlientGui() {
        klientList = Klient.getKlient();
        KlientTableModel klientTableModel = new KlientTableModel(klientList);
        JTable table = new JTable(klientTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(850,600));
        JScrollPane scrollPane = new JScrollPane(table);
        JButton buttonBack = new JButton("<-");
        buttonBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton buttonDelete = new JButton("Insert");
        buttonDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panelB = new JPanel();
        panelB.setLayout(new BorderLayout());
        JPanel panelF = new JPanel();
        JPanel paneBs = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel panelT = new JPanel();
        panelB.add(buttonBack,BorderLayout.WEST);
        paneBs.add(buttonDelete,BorderLayout.CENTER);
        panelT.add(scrollPane);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelB);
        mainPanel.add(paneBs);
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Klienci z bazy Restauracja");
        this.setVisible(true);
    }
}

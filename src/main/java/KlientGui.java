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
        Label lLogin = new Label("Login:");
        TextField login = new TextField();
        login.setPreferredSize(new Dimension(100,20));
        Label lPassword = new Label("Password:");
        TextField password = new TextField();
        password.setPreferredSize(new Dimension(100,20));
        JButton buttonBack = new JButton("<-");
        buttonBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton buttonInsert = new JButton("Insert");
        buttonInsert.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panelB = new JPanel();
        panelB.setLayout(new BorderLayout());
        JPanel panelF = new JPanel();
        JPanel paneBs = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel panelT = new JPanel();
        panelB.add(buttonBack,BorderLayout.WEST);
        paneBs.add(buttonInsert,BorderLayout.CENTER);
        panelF.add(lLogin);
        panelF.add(login);
        panelF.add(lPassword);
        panelF.add(password);
        panelT.add(scrollPane);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelB);
        mainPanel.add(panelF);
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


        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Klient.addKlient(login.getText(),password.getText());
                klientTableModel.setKlienci(Klient.getKlient());
                table.setModel(klientTableModel);
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

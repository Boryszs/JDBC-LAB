package jdbc.GUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseWindow extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(ChooseWindow.class);

    public ChooseWindow(){
        JButton button = new JButton("Wybierz");
        Panel panelMain = new Panel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        Panel panelC = new Panel();
        Panel panelB = new Panel();
        String choose [] = {"Wybierz","Klient","Ososby"};
        Label label = new Label("Wybierz baze:");
        JComboBox jCheckBox = new JComboBox(choose);
        panelC.add(label);
        panelC.add(jCheckBox);
        panelB.add(button);
        panelMain.add(panelC);
        panelMain.add(panelB);
        this.add(panelMain);
        this.setPreferredSize(new Dimension(400,150));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setTitle("Wybór tabeli");
        this.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int index = jCheckBox.getSelectedIndex();
               if(index == 1){
                   logger.info("Wybrano Tabele"+jCheckBox.getSelectedItem());
                   dispose();
                   SwingUtilities.invokeLater(new Runnable() {
                       @Override
                       public void run() {
                           new KlientGui();
                       }
                   });
               }if(index == 2){
                    logger.info("Wybrano Tabele"+jCheckBox.getSelectedItem());
                   dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new OsobaGUI();
                        }
                    });
                }
            }
        });
    }
}

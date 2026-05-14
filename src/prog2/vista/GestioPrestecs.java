package prog2.vista;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioPrestecs extends JFrame {
    private JPanel panelGestioPrestecs;
    private JButton btnRetPrestec;
    private JButton btnAfegirPrestec;
    private JCheckBox checkOnlyNoRetornats;
    private JButton btnVisualitzarPrestecs;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestioPrestecs gestioPrestecs = new GestioPrestecs();
            gestioPrestecs.setVisible(true);
        });
    }

    public GestioPrestecs(){
        setTitle("GestioPrestecs GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelGestioPrestecs);
        setSize(500,400);
        setLocationRelativeTo(null);
        btnAfegirPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        checkOnlyNoRetornats.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
    }

}

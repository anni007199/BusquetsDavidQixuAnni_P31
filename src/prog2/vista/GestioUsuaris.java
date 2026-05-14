package prog2.vista;

import javax.swing.*;

public class GestioUsuaris extends JFrame{
    private JPanel panelGestioUsuaris;
    private JTextField txtNom;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestioUsuaris gestioUsuaris = new GestioUsuaris();
            gestioUsuaris.setVisible(true);
        });
    }
    public GestioUsuaris(){
        setTitle("GestioUsuaris GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelGestioUsuaris);
        setSize(500,400);
        setLocationRelativeTo(null);
    }
}

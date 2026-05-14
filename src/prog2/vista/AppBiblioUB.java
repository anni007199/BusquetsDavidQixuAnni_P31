package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AppBiblioUB extends JFrame {

    private JPanel panelAppBiblioUB;
    private JTextField textField1;
    private JButton holaButton;
    private Adaptador adaptador;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB appBiblioUB = new AppBiblioUB();
            appBiblioUB.setVisible(true);
        });
    }
    public AppBiblioUB(){
        adaptador = new Adaptador();
        setTitle("AppBiblioUB GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelAppBiblioUB);
        setSize(500,400);
        setLocationRelativeTo(null);
        holaButton.addActionListener(new ActionListener() {
        });
    }
}

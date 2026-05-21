package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppBiblioUB extends JFrame {

    private JPanel panelAppBiblioUB;
    private Adaptador adaptador;
    private JButton btnAnarGestioUsuaris;
    private JButton btnAnarGestioExemplars;
    private JButton btnAnarGestioPrestecs;
    private JButton btnGuardarDades;
    private JButton recuperarDadesButton;
    private JButton sortirButton;

    public void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB appBiblioUB = new AppBiblioUB();
            appBiblioUB.setVisible(true);
        });



        // obra el formulari per gestionar usuaris
        btnAnarGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioUsuaris frmGestioUsuaris = new GestioUsuaris(adaptador);
                frmGestioUsuaris.pack();
                frmGestioUsuaris.setVisible(true);
            }
        });

        // obra el formulari per gestionar exemplars
        btnAnarGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioExemplars frmGestioExemplars = new GestioExemplars(adaptador);
                frmGestioExemplars.pack();
                frmGestioExemplars.setVisible(true);
            }
        });

        // obra el formulari per gestionar prestecs
        btnAnarGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioPrestecs frmGestioPrestecs = new GestioPrestecs(adaptador);
                frmGestioPrestecs.pack();
                frmGestioPrestecs.setVisible(true);
            }
        });
    }

    public AppBiblioUB(){
        adaptador = new Adaptador();
        setTitle("AppBiblioUB GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelAppBiblioUB);
        setSize(500,400);
        setLocationRelativeTo(null);

    }
}

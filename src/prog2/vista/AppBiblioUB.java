package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppBiblioUB extends JFrame {

    private JPanel panelAppBiblioUB;
    private JButton btnAnarGestioUsuaris;
    private JButton btnAnarGestioExemplars;
    private JButton btnAnarGestioPrestecs;
    private JButton btnGuardarDades;
    private JButton btnRecuperarDades;
    private JButton btnSortir;
    private Adaptador adaptador;

    // constructor
    public AppBiblioUB() {
        this.adaptador = new Adaptador();
        setContentPane(panelAppBiblioUB);
        setTitle("Biblioteca UB - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(0, 0);

        // obre el formulari per gestionar usuarios
        btnAnarGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioUsuaris frmGestioUsuaris = new GestioUsuaris(adaptador);
                frmGestioUsuaris.pack();
                frmGestioUsuaris.setVisible(true);
            }
        });

        // obre el formulari per gestionar exemplars
        btnAnarGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioExemplars frmGestioExemplars = new GestioExemplars(adaptador);
                frmGestioExemplars.pack();
                frmGestioExemplars.setVisible(true);
            }
        });

        // obre el formulari per gestionar prestecs
        btnAnarGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioPrestecs frmGestioPrestecs = new GestioPrestecs(adaptador);
                frmGestioPrestecs.pack();
                frmGestioPrestecs.setVisible(true);
            }
        });

        //
        btnGuardarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // selector de archius
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona on vols guardar el fitxer de dades");

                int seleccion = fileChooser.showSaveDialog(AppBiblioUB.this);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fitxer = fileChooser.getSelectedFile();
                    try {
                        adaptador.guardaDades(fitxer.getAbsolutePath());
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades guardades correctament.");
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "ERROR en guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //
        btnRecuperarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona el fitxer de dades a carregar");
                int seleccion = fileChooser.showOpenDialog(AppBiblioUB.this);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fitxer = fileChooser.getSelectedFile();
                    try {
                        adaptador.carregaDades(fitxer.getAbsolutePath());
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades carregades correctament.");
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "ERROR en carregar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //
        btnSortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     *
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB appBiblioUB = new AppBiblioUB();
            appBiblioUB.pack(); // Ajusta la ventana al tamaño de los componentes del .form
            appBiblioUB.setVisible(true);
        });
    }
}
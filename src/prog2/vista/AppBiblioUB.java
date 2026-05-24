package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Finestra principal de l'aplicació gràfica BiblioUB.
 * Aquesta classe actua com a menú principal mitjançant una interfície JFrame.
 * Permet a l'usuari navegar cap a les diferents seccions de gestió (Usuaris, Exemplars i Préstecs)
 * i ofereix les opcions de guardar i carregar les dades en/des-de fitxer
 */
public class AppBiblioUB extends JFrame {

    private JPanel panelAppBiblioUB;
    private JButton btnAnarGestioUsuaris;
    private JButton btnAnarGestioExemplars;
    private JButton btnAnarGestioPrestecs;
    private JButton btnGuardarDades;
    private JButton btnRecuperarDades;
    private JButton btnSortir;
    private Adaptador adaptador;

    /**
     * Constructor per defecte de la classe AppBiblioUB.
     * S'encarrega d'inicialitzar l'adaptador lògic central, configurar els paràmetres
     * estructurals de la finestra principal (com el títol, el comportament de tancament i la posició)
     * i assignar els controladors d'esdeveniments (ActionListeners) a cadascun dels botons del menú.
     */
    public AppBiblioUB() {
        this.adaptador = new Adaptador();
        setContentPane(panelAppBiblioUB);
        setTitle("Biblioteca UB - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(0, 0);

        /**
         * Obra el formulari per gestionar usuaris
         */
        btnAnarGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioUsuaris frmGestioUsuaris = new GestioUsuaris(adaptador);
                frmGestioUsuaris.pack();
                frmGestioUsuaris.setVisible(true);
            }
        });

        /**
         * Obra el formulari per gestionar exemplars
         */
        btnAnarGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioExemplars frmGestioExemplars = new GestioExemplars(adaptador);
                frmGestioExemplars.pack();
                frmGestioExemplars.setVisible(true);
            }
        });

        /**
         * Obra el formulari per gestionar prestecs
         */
        btnAnarGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioPrestecs frmGestioPrestecs = new GestioPrestecs(adaptador);
                frmGestioPrestecs.pack();
                frmGestioPrestecs.setVisible(true);
            }
        });

        /**
         * Controlador d'esdeveniments per desar les dades en un fitxer serialitzat
         */
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
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "ERROR en guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        /**
         * Controlador d'esdeveniments per carregar i restaurar el model de dades des d'un fitxer serialitzat
         */
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

        /**
         * Controlador d'esdeveniments per al botó de sortir.
         * Tanca la finestra
         */
        btnSortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Inicialitza la interfície gràfica d'usuari assegurant l'execució
     * d'esdeveniments de Swing (Event Dispatch Thread) mitjançant SwingUtilities.
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB appBiblioUB = new AppBiblioUB();
            appBiblioUB.pack(); // Ajusta la ventana al tamaño de los componentes del .form
            appBiblioUB.setVisible(true);
        });
    }
}
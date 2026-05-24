package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra de diàleg per introduir les dades i registrar un nou préstec.
 * Aquesta classe proporciona un formulari modal que utilitza llistes desplegables
 * per seleccionar de manera gràfica quin usuari i quin exemplar s'associaran en
 * el nou préstec, validant les posicions seleccionades abans de demanar l'alta.
 */
public class FrmAfegirPrestec extends JDialog {
    private JPanel panelAfegirPrestecs;
    private JComboBox<String> cmbUsuaris;
    private JComboBox<String> cmbExemplars;
    private JCheckBox chkEsLlarg;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel etPosicioExemplar;
    private JLabel etPosicioUsuari;

    private Adaptador adaptador;

    /**
     * Constructor de la classe FrmAfegirPrestec.
     * Rep la instància de l'adaptador central, configura el panell de contingut gràfic,
     * defineix el diàleg com a modal perquè bloquegi la pantalla de fons, invoca el procés
     * privat d'omplert de les llistes desplegables i programa els controladors d'esdeveniments.
     * * @param adaptador Instància del controlador o adaptador de dades compartit per tota l'aplicació.
     */
    public FrmAfegirPrestec(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelAfegirPrestecs);
        setModal(true); // bloquea la ventana de atrás hasta que se cierre
        setTitle("Afegir Nou Préstec");
        setLocation(0, 0);

        carregarCombos(); // Llenar los ComboBox con los datos actuales del sistema usando el adaptador

        /**
         * Controlador d'esdeveniments per al botó de confirmar.
         * Extrau els índexs numèrics seleccionats a les llistes de dalt de la finestra.
         * Verifica que s'hagi fet una selecció real i
         * demana l'alta del préstec al model lògic a través de l'adaptador.
         * Atrapa gràficament qualsevol excepció o denegació.
         */
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int usuariPos = cmbUsuaris.getSelectedIndex();
                int exemplarPos = cmbExemplars.getSelectedIndex();
                boolean esLlarg = chkEsLlarg.isSelected();

                if (usuariPos == -1 || exemplarPos == -1) { // -1 significa que está vacío
                    JOptionPane.showMessageDialog(null, "Cal seleccionar un usuari i un exemplar.");
                    return;
                }

                try {
                    adaptador.afegirPrestec(exemplarPos, usuariPos, esLlarg);
                    JOptionPane.showMessageDialog(null, "Préstec afegit correctament.");
                    dispose(); // Cierra esta ventana emergente
                } catch (BiblioException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage(), "Error de validació", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /**
         * Controlador d'esdeveniments per al botó de cancel·lar.
         * Tanca la finestra
         */
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Mètode privat i auxiliar per poblar les llistes desplegables de la interfície.
     * Neteja qualsevol element residual existent dins de cmbUsuaris i cmbExemplars, demana
     * els registres en format de text a l'adaptador i, mitjançant bucles seccionalitzats,
     * afegeix cada línia de descripció com un ítem seleccionable dins dels ComboBox gràfics.
     */
    private void carregarCombos() {
        cmbUsuaris.removeAllItems();
        cmbExemplars.removeAllItems();

        // Añadimos las listas en formato String
        for (String usuari : adaptador.getLlistaUsuaris()) {
            cmbUsuaris.addItem(usuari);
        }
        for (String exemplar : adaptador.getLlistaExemplars()) {
            cmbExemplars.addItem(exemplar);
        }
    }
}
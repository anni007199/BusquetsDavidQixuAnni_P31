package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra de diàleg per a la gestió del flux de préstecs de la biblioteca.
 * Aquesta classe permet visualitzar l'historial de préstecs actius i tancats,
 * aplicar filtres dinàmics per veure només aquells que no s'han retornat,
 * processar devolucions de llibres i enllaçar amb el formulari d'alta.
 */
public class GestioPrestecs extends JDialog {
    private JPanel panelGestioPrestecs;
    private JButton btnAfegirPrestec;
    private JScrollPane scrollPane;
    private JList<String> visualitzarPrestecs;
    private JButton btnRetornarPrestec;
    private JCheckBox chkOnlyNoRetornats;
    private JButton btnSortir;
    private Adaptador adaptador;


    /**
     * Constructor de la classe GestioPrestecs.
     * Assigna el controlador de dades centralitzat, associa el panell de contingut gràfic,
     * configura la modalitat del diàleg perquè bloquegi la finestra principal de fons,
     * invoca la sincronització inicial de dades de l'historial i programa els controladors d'esdeveniments.
     * * @param adaptador Instància del controlador o adaptador de dades compartit per tota l'aplicació.
     */
    public GestioPrestecs(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioPrestecs);
        setModal(true);
        setTitle("Gestió de Préstecs");
        setLocation(0, 0);
        actualitzarLlistaPrestecs();


        /**
         * Controlador d'esdeveniments per al botó d'obertura del formulari d'alta.
         * Instancia de manera modal el diàleg FrmAfegirPrestec passant l'adaptador com a paràmetre.
         * Un cop tancat el formulari, el codi es repren i es refresca automàticament el llistat gràfic.
         */
        btnAfegirPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirPrestec frmAfegirPrestec = new FrmAfegirPrestec(adaptador);
                frmAfegirPrestec.pack();
                frmAfegirPrestec.setVisible(true);
                actualitzarLlistaPrestecs(); // actualitzar la llista al sortir
            }
        });

        /**
         * Controlador d'esdeveniments per al botó de retorn de préstecs.
         * Identifica l'índex seleccionat per l'usuari a la llista gràfica. Si n'hi ha un de triat,
         * demana l'operació al model a través de l'adaptador i llança avisos visuals tant per
         * confirmar l'èxit com per advertir en cas d'errors de consistència o manca de selecció.
         */
        btnRetornarPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSeleccionat = visualitzarPrestecs.getSelectedIndex();
                if (indexSeleccionat != -1) {
                    try {
                        adaptador.retornarPrestec(indexSeleccionat);
                        JOptionPane.showMessageDialog(null, "Préstec retornat correctament.");
                        actualitzarLlistaPrestecs();
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un préstec de la llista.");
                }
            }
        });

        /**
         * Controlador d'esdeveniments per a la casella de selecció de filtre.
         * Detecta el canvi d'estat a la casella i reactiva el mètode de refresc,
         * provocant que la llista es reconstrueixi segons el criteri de filtratge marcat.
         */
        chkOnlyNoRetornats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarLlistaPrestecs();
            }
        });

        /**
         * Controlador d'esdeveniments per al botó de tancar la finestra.
         * Allibera els recursos gràfics associats a aquest diàleg secundari i en tanca la visualització.
         */
        btnSortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    /**
     * Mètode privat i auxiliar encarregat de sincronitzar i reconstruir l'estat del JList.
     * Evalua l'estat de la casella de filtratge: si està seleccionada, demana a l'adaptador només els
     * préstecs no retornats; en cas contrari, recupera l'historial global. Transfereix la col·lecció de dades
     * a un model de Swing pròpi i actualitza el component gràfic per a l'usuari.
     */
    private void actualitzarLlistaPrestecs() {
        DefaultListModel<String> model = new DefaultListModel<>();
        java.util.ArrayList<String> prestecs;

        if (chkOnlyNoRetornats.isSelected()) {
            prestecs = adaptador.getLlistaPrestecsNoRetornats();
        } else {
            prestecs = adaptador.getLlistaPrestecs();
        }

        for (String p : prestecs) {
            model.addElement(p);
        }
        visualitzarPrestecs.setModel(model);
    }

}


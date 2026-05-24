package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra de diàleg per a la gestió d'exemplars de la biblioteca.
 * Aquesta classe ofereix una interfície gràfica basada en un diàleg que
 * permet l'usuari visualitzar el catàleg d'exemplars registrats al
 * sistema, actuant alhora com a enllaç per invocar el formulari d'alta d'exeplars.
 */
public class GestioExemplars extends JDialog {
    private JList<String> listExemplars;
    private JButton btnObrirFormulariAfegir;
    private Adaptador adaptador;
    private JPanel panelGestioExemplars;
    private JButton btnSortir;


    /**
     * Constructor de la classe GestioExemplars.
     * Assigna la instància de l'adaptador lògic central, inicialitza el panell de contingut,
     * defineix el diàleg com a modal per bloquejar la interacció amb la finestra del fons,
     * sincronitza el llistat inicial d'exemplars i configura els controladors d'esdeveniments.
     * * @param adaptador Instància del controlador o adaptador de dades compartit per tota l'aplicació.
     */
    public GestioExemplars(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioExemplars);
        setModal(true);
        setTitle("Gestió d'Exemplars - BiblioUB");
        setLocation(0, 0);
        actualitzarLlistaExemplars();

        /**
         * Controlador d'esdeveniments per al botó d'obertura del formulari d'alta.
         * Instancia de manera modal el diàleg FrmAfegirExemplar passant l'adaptador com a paràmetre.
         * Un cop es tanca el formulari, el flux continua i es refresca automàticament el llistat.
         */
        btnObrirFormulariAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirExemplar frmAfegir = new FrmAfegirExemplar(adaptador);
                frmAfegir.pack();
                frmAfegir.setVisible(true);
                actualitzarLlistaExemplars();
            }
        });


        /**
         * Controlador d'esdeveniments per al botó de sortir.
         * Tanca la finestra
         */
        btnSortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    /**
     * Mètode privat i auxiliar encarregat de sincronitzar l'estat de la llista gràfica d'exemplars.
     * Recupera l'ArrayList amb les cadenes de text directament des del controlador,
     * buida el contingut estructural en un DefaultListModel de Swing i actualitza el JList
     * perquè reflecteixi els canvis en temps real sobre la pantalla.
     */
    private void actualitzarLlistaExemplars() {
        DefaultListModel<String> modelGrafic = new DefaultListModel<>();
        java.util.ArrayList<String> llistaExemplarsSistema = adaptador.getLlistaExemplars();
        for (String exemplar : llistaExemplarsSistema) {
            modelGrafic.addElement(exemplar);
        }

        listExemplars.setModel(modelGrafic);
    }
}
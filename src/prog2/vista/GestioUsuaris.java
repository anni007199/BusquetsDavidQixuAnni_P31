package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestres de diàleg per a la gestió d'usuaris de la biblioteca BiblioUB.
 * Aquesta classe proporciona una interfície gràfica que permet l'usuari
 * visualitzar de forma síncrona la llista de tots els usuaris registrats
 * en el sistema, així com actuar d'enllaç per obrir el formulari d'alta de nous usuaris.
 */
public class GestioUsuaris extends JDialog {
    private JPanel panelGestioUsuaris;
    private JList<String> listUsuaris;
    private JButton btnAfegirUsuari;
    private JButton btnSortirGestioUsuaris;
    private Adaptador adaptador;


    /**
     * Constructor de la classe GestioUsuaris.
     * Assigna la instància de l'adaptador global, inicialitza les propietats d'estat
     * i modalitat del JDialog, sincronitza les dades inicials i configura els controladors
     * d'esdeveniments per als elements interactius.
     * * @param adaptador Instància del adaptador de dades compartit per tota l'aplicació.
     */
    public GestioUsuaris(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioUsuaris);
        // Controlar el marc dels botons (per quedar més bé l'estàtica)
        btnAfegirUsuari.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        btnSortirGestioUsuaris.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        setModal(true);
        setTitle("Gestió d'Usuaris - BiblioUB");
        setLocation(0, 0);
        actualitzarLlistaUsuaris();


        /**
         * Controlador d'esdeveniments per al botó d'obertura del formulari d'alta.
         * Instancia de manera modal el diàleg FrmAfegirUsuari passant l'adaptador com a paràmetre.
         * Un cop es tanca el formulari, el flux continua i es refresca automàticament el llistat.
         */
        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari frmAfegir = new FrmAfegirUsuari(adaptador);
                frmAfegir.pack();
                frmAfegir.setVisible(true);
                actualitzarLlistaUsuaris();
            }
        });

        /**
         * Controlador d'esdeveniments per al botó de sortir.
         * Tanca la finestra
         */
        btnSortirGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    /**
     * Mètode privat i auxiliar encarregat de sincronitzar l'estat de la llista gràfica.
     * Recupera la col·lecció de cadenes de text des de l'adaptador,
     * genera un nou objecte de tipus DefaultListModel pròpi de Swing i el porta
     * sobre el component de la vista per refrescar la llista mostrada en pantalla.
     */
    private void actualitzarLlistaUsuaris() {
        DefaultListModel<String> modelGrafic = new DefaultListModel<>();
        java.util.ArrayList<String> llistaUsuarisSistema = adaptador.getLlistaUsuaris();
        for (String i : llistaUsuarisSistema) {
            modelGrafic.addElement(i);
        }

        listUsuaris.setModel(modelGrafic);
    }
}
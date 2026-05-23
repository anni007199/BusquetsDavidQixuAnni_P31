package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestioUsuaris extends JDialog {
    private JPanel panelGestioUsuaris;
    private JList<String> listUsuaris;
    private JButton btnAfegirUsuari;
    private JButton btnSortirGestioUsuaris;
    private Adaptador adaptador;


    public GestioUsuaris(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioUsuaris);
        // Controlar el marc dels botons (per quedar més bé l'estàtica)
        btnAfegirUsuari.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        btnSortirGestioUsuaris.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        setModal(true);
        setTitle("Gestió d'Usuaris - BiblioUB");
        setLocationRelativeTo(null);
        actualitzarLlistaUsuaris();


        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari frmAfegir = new FrmAfegirUsuari(adaptador);
                frmAfegir.pack();
                frmAfegir.setVisible(true);
                actualitzarLlistaUsuaris();
            }
        });

        btnSortirGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    private void actualitzarLlistaUsuaris() {
        DefaultListModel<String> modelGrafic = new DefaultListModel<>();
        java.util.ArrayList<String> llistaUsuarisSistema = adaptador.getLlistaUsuaris();
        for (String i : llistaUsuarisSistema) {
            modelGrafic.addElement(i);
        }

        listUsuaris.setModel(modelGrafic);
    }
}
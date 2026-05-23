package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestioExemplars extends JDialog {
    private JList<String> listExemplars;
    private JButton btnObrirFormulariAfegir;
    private Adaptador adaptador;
    private JPanel panelGestioExemplars;
    private JButton btnSortir;


    public GestioExemplars(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioExemplars);
        setModal(true);
        setTitle("Gestió d'Exemplars - BiblioUB");
        setLocationRelativeTo(null);
        actualitzarLlistaExemplars();

        btnObrirFormulariAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirExemplar frmAfegir = new FrmAfegirExemplar(adaptador);
                frmAfegir.pack();
                frmAfegir.setVisible(true);
                actualitzarLlistaExemplars();
            }
        });


        btnSortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    private void actualitzarLlistaExemplars() {
        DefaultListModel<String> modelGrafic = new DefaultListModel<>();
        java.util.ArrayList<String> llistaExemplarsSistema = adaptador.getLlistaExemplars();
        for (String exemplar : llistaExemplarsSistema) {
            modelGrafic.addElement(exemplar);
        }

        listExemplars.setModel(modelGrafic);
    }
}
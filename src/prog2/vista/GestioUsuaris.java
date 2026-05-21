package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioUsuaris extends JFrame{
    private JPanel panelGestioUsuaris;
    private JScrollPane scrollPane;
    private JList<String> visualitzarUsuaris;
    private JButton btnAfegirUsuari;
    private JButton btnSortirGestioUsuaris;
    private Adaptador adaptador;


    public GestioUsuaris(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioUsuaris);
        setModal(true);
        setTitle("Gestió de Usuaris");

        actualitzarLlista();

        // obra el formulari perf afegir un usuari
        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari frmAfegirUsuari = new FrmAfegirUsuari(adaptador);
                frmAfegirUsuari.pack();
                frmAfegirUsuari.setVisible(true);
                actualitzarLlista(); // actualitzar la llista al sortir
            }
        });


    }

    private void actualitzarLlista() {
        DefaultListModel<String> model = new DefaultListModel<>();
        java.util.ArrayList<String> usuaris;

        usuaris = adaptador.getLlistaUsuaris();
        for (String p : usuaris) {
            model.addElement(p);
        }
        visualitzarUsuaris.setModel(model);
    }
}

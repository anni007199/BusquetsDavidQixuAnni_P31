package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioPrestecs extends JDialog { // O JPanel, según cómo tengáis la ventana principal
    private JPanel panelGestioPrestecs;
    private JButton btnAfegirPrestec;
    private JScrollPane scrollPane;
    private JList<String> visualitzarPrestecs;
    private JButton btnRetornarPrestec;
    private JCheckBox chkOnlyNoRetornats;
    private Adaptador adaptador;

    public GestioPrestecs(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelGestioPrestecs);
        setModal(true);
        setTitle("Gestió de Préstecs");

        actualitzarLlista();

        //obra el formulari perf afegir un prestec
        btnAfegirPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirPrestec frmAfegir = new FrmAfegirPrestec(adaptador);
                frmAfegir.pack();
                frmAfegir.setVisible(true);
                actualitzarLlista(); // actualitzar la llista al sortir
            }
        });

        // obra el formulari per obrir un prestec
        btnRetornarPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSeleccionat = visualitzarPrestecs.getSelectedIndex();
                if (indexSeleccionat != -1) {
                    try {
                        adaptador.retornarPrestec(indexSeleccionat);
                        JOptionPane.showMessageDialog(null, "Préstec retornat correctament.");
                        actualitzarLlista();
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un préstec de la llista.");
                }
            }
        });

        chkOnlyNoRetornats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarLlista();
            }
        });
    }

    private void actualitzarLlista() {
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



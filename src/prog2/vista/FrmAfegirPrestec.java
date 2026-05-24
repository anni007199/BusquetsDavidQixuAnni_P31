package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public FrmAfegirPrestec(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelAfegirPrestecs);
        setModal(true); // bloquea la ventana de atrás hasta que se cierre
        setTitle("Afegir Nou Préstec");
        setLocation(0, 0);

        carregarCombos(); // Llenar los ComboBox con los datos actuales del sistema usando el adaptador

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

        // Botón Cancelar, cierra la ventana sin hacer nada
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

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
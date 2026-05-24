package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrmAfegirExemplar extends JDialog {
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JCheckBox chkAdmetPrestecLlarg;
    private JButton btnAcceptar;
    private JButton btnCancelar;
    private JLabel etID;
    private JLabel etTitol;
    private JLabel etAutor;
    private Adaptador adaptador;


    public FrmAfegirExemplar(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Afegir Nou Exemplar");
        setLocation(0, 0);

        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                String titol = txtTitol.getText().trim();
                String autor = txtAutor.getText().trim();
                boolean admetPrestecLlarg = chkAdmetPrestecLlarg.isSelected();

                if (id.isEmpty() || titol.isEmpty() || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(FrmAfegirExemplar.this,
                            "Tots els camps (ID, Títol i Autor) són obligatoris.",
                            "Camps incomplets", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    adaptador.afegirExemplar(id, titol, autor, admetPrestecLlarg);
                    JOptionPane.showMessageDialog(FrmAfegirExemplar.this, "Exemplar registrat correctament.");
                    dispose();

                } catch (BiblioException ex) {
                    JOptionPane.showMessageDialog(FrmAfegirExemplar.this,
                            "ERROR: " + ex.getMessage(),
                            "Error de validació", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // BOTÓN: Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
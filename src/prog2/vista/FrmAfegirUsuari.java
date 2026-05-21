package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JTextField txtEmail;
    private JTextField txtNom;
    private JTextField txtAdreca;
    private JCheckBox chkEsEstudiant;
    private JButton btnAcceptar;
    private JButton btnCancelar;
    private Adaptador adaptador;


    public FrmAfegirUsuari(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Afegir Nou Usuari");
        setLocationRelativeTo(null);

        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String nom = txtNom.getText().trim();
                String adreca = txtAdreca.getText().trim();
                boolean esEstudiant = chkEsEstudiant.isSelected();

                if (email.isEmpty() || nom.isEmpty() || adreca.isEmpty()) {
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this,
                            "Tots els camps són obligatoris per registrar l'usuari.",
                            "Camps incomplets", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this, "Usuari registrat correctament.");
                    dispose();

                } catch (BiblioException ex) {
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this,
                            "ERROR: " + ex.getMessage(),
                            "Error al duplicar usuari", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
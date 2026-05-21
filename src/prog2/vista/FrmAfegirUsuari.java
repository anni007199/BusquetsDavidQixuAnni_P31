package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.*;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JCheckBox chkEstudiant;
    private JComboBox cmbEmail;
    private JComboBox cmbNom;
    private JComboBox cmbAdreca;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public FrmAfegirUsuari(Adaptador adaptador) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnConfirmar);


        // Botón Cancelar, cierra la ventana sin hacer nada
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


    }

    private void carregarCombos() {
        cmbEmail.removeAllItems();
        cmbNom.removeAllItems();
        cmbAdreca.removeAllItems();

        // Añadimos las listas en formato String
        for (String usuari : adaptador.getLlistaUsuaris()) {
            cmbEm.addItem(usuari);
        }
        for (String exemplar : adaptador.getLlistaExemplars()) {
            cmbExemplars.addItem(exemplar);
        }
    }
}

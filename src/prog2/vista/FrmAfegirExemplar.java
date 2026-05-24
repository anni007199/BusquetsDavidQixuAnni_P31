package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra de diàleg per introduir les dades i registrar un nou exemplar al catàleg.
 * Aquesta classe proporciona un formulari que demana el codi d'identificació,
 * el títol, l'autor i les condicions de préstec de l'exemplar, verificant que no
 * hi hagi camps buits abans de transferir la informació al model de dades.
 */
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


    /**
     * Constructor de la classe FrmAfegirExemplar.
     * Rep l'adaptador lògic central del sistema, enllaça el panell de contingut principal,
     * defineix la finestra com a modal perquè l'usuari hagi de tancar-la abans de tornar enrere
     * i programa la lògica interna dels botons d'acceptació i cancel·lació.
     * * @param adaptador Instància del controlador de dades compartit per tota l'aplicació.
     */
    public FrmAfegirExemplar(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Afegir Nou Exemplar");
        setLocation(0, 0);

        /**
         * Controlador d'esdeveniments per al botó d'acceptar.
         * Recupera les dades introduïdes de l'exemplar netejant espais en blanc
         * Avalua si els camps requerits estan complets i el dona d'alta
         * mitjançant l'adaptador. Gestiona de forma gràfica tant l'èxit com les excepcions del model.
         */
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

        /**
         * Controlador d'esdeveniments per al botó de cancel·lar.
         * Tanca la finestra
         */
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
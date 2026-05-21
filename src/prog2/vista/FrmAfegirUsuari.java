package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.Usuari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrmAfegirUsuari extends JDialog {
    private Adaptador adaptador;
    private JList<String> llistaUsuaris;
    private DefaultListModel<String> modelLlista;
    private JButton afegirButton;
    private JButton tancarButton;

    private JPanel contentPane;

    public FrmAfegirUsuari(JFrame finestraPare, Adaptador adaptador) {
        super(finestraPare,"Gestió Usuaris", true);
        this.adaptador = adaptador;

        setSize(400,300);
        setLocationRelativeTo(finestraPare);
        inici();
        afegirEvents();
        actualitzarLlista();
        }

    private void inici(){
        setLayout(new BorderLayout());
        modelLlista = new DefaultListModel<>();
        llistaUsuaris = new JList<>(modelLlista);
        JScrollPane scrollPane = new JScrollPane(llistaUsuaris);

        JPanel panelButons  = new JPanel(new FlowLayout());
        afegirButton = new JButton("Afegir Usuari");
        tancarButton = new JButton("Tancar");
        panelButons.add(afegirButton);
        panelButons.add(tancarButton);

        JPanel panelNom = new JPanel();
        JLabel titol = new JLabel("Llista d'usuaris: ");
        panelNom.add(titol);

        add(panelNom,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(panelButons,BorderLayout.SOUTH);
    }

    // Afegir liseners als buttons
    private void afegirEvents(){
        afegirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrirDialogAfegirUsuari();
            }
        });
        tancarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void obrirDialogAfegirUsuari(){
        FrmAfegirUsuari dialog = new FrmAfegirUsuari(this,adaptador);
        dialog.setVisible(true);
        actualitzarLlista();
    }
    private void actualitzarLlista(){
        modelLlista.clear();
        java.util.ArrayList<String> llistaUsuaris = adaptador.getLlistaUsuaris();
        for (String usuari:llistaUsuaris){
            modelLlista.addElement(usuari);
        }
    }
}

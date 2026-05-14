package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AppBiblioUB extends JFrame {

    private JPanel panelAppBiblioUB;
    private JTextField textField1;
    private JButton holaButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB appBiblioUB = new AppBiblioUB();
            appBiblioUB.setVisible(true);
        });
    }
    public AppBiblioUB(){
        setTitle("AppBiblioUB GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelAppBiblioUB);
        setSize(500,400);
        setLocationRelativeTo(null);
        holaButton.addActionListener(new ActionListener() {
        });
    }
}

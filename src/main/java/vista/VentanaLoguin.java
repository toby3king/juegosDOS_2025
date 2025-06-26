package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaLoguin extends JFrame {

    private JPanel contentPane;
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JButton iniciarSesionButton;

    public VentanaLoguin() {
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null); // centra la ventana

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nombreLabel = new JLabel("Nombre:");
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        contentPane.add(nombreField);

        contentPane.add(Box.createRigidArea(new Dimension(0, 10))); // espacio vertical

        iniciarSesionButton = new JButton("Iniciar sesión");
        iniciarSesionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(iniciarSesionButton);

        setContentPane(contentPane);

        SwingUtilities.getRootPane(iniciarSesionButton).setDefaultButton(iniciarSesionButton);



    }


    public void clickIniciar(ActionListener e) {
        iniciarSesionButton.addActionListener(e);
    }

    public String getNombreUsuario()
    {
        return nombreField.getText();
    }
}


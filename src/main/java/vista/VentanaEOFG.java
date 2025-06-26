package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaEOFG extends JFrame {

    private JPanel contentPane;
    private JLabel ganadorLabel;
    private JButton salirButton;

    public VentanaEOFG(String nombreGanador) {
        setTitle("Fin del Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar ventana

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        // Label del ganador
        ganadorLabel = new JLabel("¡Ganó " + nombreGanador + "!");
        ganadorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ganadorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPane.add(ganadorLabel, BorderLayout.CENTER);

        // Botón para salir
        salirButton = new JButton("Salir");
        JPanel botonPanel = new JPanel();
        botonPanel.add(salirButton);
        contentPane.add(botonPanel, BorderLayout.SOUTH);


    }

    // Getter para el botón por si el controlador quiere agregar un listener
    public void setSalirButton(ActionListener e) {
         salirButton.addActionListener(e);
    }
}


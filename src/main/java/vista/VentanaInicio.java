package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {

    private JPanel contentPane;
    private JLabel cantJugadores;
    private JTextField nJugadores;
    private JButton init;

    public VentanaInicio() {
        // Configuración básica de la ventana
        setTitle("Inicio del Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal y establecer el layout
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // márgenes

        // Crear los componentes
        cantJugadores = new JLabel("Ingrese la cantidad de jugadores:");
        cantJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);

        nJugadores = new JTextField();
        nJugadores.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Alto fijo, ancho expansible

        init = new JButton("Jugar");
        init.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar los componentes al panel
        contentPane.add(cantJugadores);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical
        contentPane.add(nJugadores);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical
        contentPane.add(init);

        // Establecer el content pane
        setContentPane(contentPane);


    }

    // Getters para que puedas añadir listeners después
    public String getNJugadores() {
        return nJugadores.getText();
    }

    public void setInitButton(ActionListener e) {
         init.addActionListener(e);
    }
}


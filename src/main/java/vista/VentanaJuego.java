package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaJuego extends JFrame {

    private JPanel contentPane;
    private JTextField mensajeField;
    private JPanel panelMesa;
    private JPanel panelMano;
    private JTextField comandoField;
    private JButton realizarButton;

    private List<JLabel> cartasMesaLabels;
    private List<JLabel> cartasManoLabels;

    public VentanaJuego() {
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null); // Centra la ventana

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Campo superior de mensajes
        mensajeField = new JTextField();
        mensajeField.setEditable(false);//no se puede introducir txt solo aviso
        mensajeField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(mensajeField, BorderLayout.NORTH);

        // Panel de la mesa
        panelMesa = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelMesa.setBorder(BorderFactory.createTitledBorder("Mesa"));
        contentPane.add(panelMesa, BorderLayout.CENTER);

        // Panel inferior combinado
        JPanel panelSur = new JPanel(new BorderLayout(5, 5));

        // Tu mano
        panelMano = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelMano.setBorder(BorderFactory.createTitledBorder("Tu mano"));
        panelSur.add(panelMano, BorderLayout.CENTER);

        // Subpanel para comando + botón
        JPanel comandoPanel = new JPanel(new BorderLayout(5, 5));
        comandoField = new JTextField();
        comandoField.setHorizontalAlignment(JTextField.CENTER);
        comandoPanel.add(comandoField, BorderLayout.CENTER);

        realizarButton = new JButton("Realizar");
        comandoPanel.add(realizarButton, BorderLayout.EAST);

        panelSur.add(comandoPanel, BorderLayout.SOUTH);

        contentPane.add(panelSur, BorderLayout.SOUTH);

        // Inicializamos listas
        cartasMesaLabels = new ArrayList<>();
        cartasManoLabels = new ArrayList<>();


    }

    // Actualiza las cartas de la mesa
    public void actualizarMesa(List<String> cartas) {
        panelMesa.removeAll();
        cartasMesaLabels.clear();
        for (String carta : cartas) {
            JLabel label = new JLabel(carta);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cartasMesaLabels.add(label);
            panelMesa.add(label);
        }
        panelMesa.revalidate();
        panelMesa.repaint();
    }

    // Actualiza las cartas de la mano
    public void actualizarMano(List<String> cartas) {
        panelMano.removeAll();
        cartasManoLabels.clear();
        for (String carta : cartas) {
            JLabel label = new JLabel(carta);
            label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            cartasManoLabels.add(label);
            panelMano.add(label);
        }
        panelMano.revalidate();
        panelMano.repaint();
    }

    // Setter del mensaje superior
    public void setMensaje(String mensaje) {
        mensajeField.setText(mensaje);
    }

    // Getters útiles para el controlador
    public String getComandoField() {
        return comandoField.getText();
    }

    public void setRealizarButton(ActionListener e) {
         realizarButton.addActionListener(e);
    }

    void limpiarComandField()
    {
        comandoField.setText("");
    }
}

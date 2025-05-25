// PomodoroView.java
package vista;

import javax.swing.*;
import java.awt.*;

public class PomodoroView extends JFrame {
    public JButton btnIniciar;
    public JButton btnPausar;
    public JButton btnDetener;
    private final JLabel lblTipo;    // Muestra el tipo de sesión (TRABAJO o DESCANSO)
    private final JLabel lblEstado;  // Muestra el estado actual del temporizador
    private final JLabel lblTiempo;  // Muestra el tiempo restante en formato mm:ss

    public PomodoroView() {
        // Configuración básica de la ventana
        setTitle("Pomodoro Personalizado");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal que contiene los textos centrales
        JPanel panelCentro = new JPanel(new GridLayout(3, 1));

        // Texto inicial del tipo de sesión
        lblTipo = new JLabel("Esperando...", SwingConstants.CENTER);
        lblTipo.setFont(new Font("Arial", Font.BOLD, 24));

        // Texto inicial del estado del temporizador
        lblEstado = new JLabel("Presiona iniciar", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 18));

        // Texto inicial del tiempo
        lblTiempo = new JLabel("00:00", SwingConstants.CENTER);
        lblTiempo.setFont(new Font("Arial", Font.PLAIN, 36));

        // Agregar las etiquetas al panel central
        panelCentro.add(lblTipo);
        panelCentro.add(lblEstado);
        panelCentro.add(lblTiempo);

        // Panel de botones con controles de temporizador
        JPanel panelBotones = new JPanel();
        btnIniciar = new JButton("Iniciar Pomodoro");
        btnPausar = new JButton("Pausar");
        btnDetener = new JButton("Detener");

        panelBotones.add(btnIniciar);
        panelBotones.add(btnPausar);
        panelBotones.add(btnDetener);

        // Agregar paneles al frame
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Establece el texto del tipo de sesión
    public void setTipoTexto(String texto) {
        lblTipo.setText(texto);
    }

    // Establece el texto del estado del temporizador
    public void setEstadoTexto(String texto) {
        lblEstado.setText(texto);
    }

    // Actualiza el texto del tiempo restante
    public void actualizarEstado(String tiempo) {
        lblTiempo.setText(tiempo);
    }

    // Muestra un mensaje emergente
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Pregunta al usuario con una ventana de confirmación
    public int preguntar(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    // Cierra la ventana y termina la aplicación
    public void cerrarVentana() {
        dispose(); // Cierra la ventana actual
        System.exit(0); // Finaliza el programa
    }
}

import controlador.PomodoroController;
import vista.PomodoroView;

// Clase principal de la aplicación Pomodoro
public class Main {
    public static void main(String[] args) {
        // Ejecuta la creación de la interfaz gráfica en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            PomodoroView vista = new PomodoroView(); // Crea la vista (interfaz gráfica)
            new PomodoroController(vista); // Crea el controlador y lo conecta con la vista
        });
    }
}
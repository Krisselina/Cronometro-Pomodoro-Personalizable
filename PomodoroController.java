package controlador;

import modelo.*;
import vista.PomodoroView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroController {
    private final Usuario usuario;
    private final Musica musica;
    private Temporizador temporizador;

    public PomodoroController(PomodoroView vista) {
        this.usuario = new Usuario();
        this.musica = new Musica();

        // Configura al usuario (lee config.txt o lo solicita)
        usuario.configurar();

        // Clase anónima para manejar el clic del botón "Iniciar"
        vista.btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temporizador = new Temporizador(usuario.getTiempoTrabajo(), usuario.getTiempoDescanso(), vista, musica);
                musica.reproducir(usuario.getPlaylistTrabajo());
                temporizador.iniciarTrabajo(() -> {
                    musica.detener();
                    musica.reproducirAlarma();
                    int respuestaTrabajo = vista.preguntar("¿Quieres iniciar el descanso?");
                    musica.detener(); // Asegura que se detenga la alarma antes de continuar
                    if (respuestaTrabajo != 0) {
                        vista.cerrarVentana();
                    } else {
                        temporizador = new Temporizador(usuario.getTiempoTrabajo(), usuario.getTiempoDescanso(), vista, musica);
                        musica.reproducir(usuario.getPlaylistDescanso());
                        temporizador.iniciarDescanso(() -> {
                            musica.detener();
                            musica.reproducirAlarma();
                            int respuestaCiclo = vista.preguntar("¿Deseas iniciar un nuevo ciclo?");
                            musica.detener(); // Asegura que se detenga la alarma antes de continuar
                            if (respuestaCiclo != 0) {
                                vista.cerrarVentana();
                            } else {
                                vista.btnIniciar.doClick();
                            }
                        });
                    }
                });
            }
        });

        // Clase anónima para manejar el botón "Pausar"
        vista.btnPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (temporizador != null) temporizador.pausar();
            }
        });

        // Clase anónima para manejar el botón "Detener"
        vista.btnDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (temporizador != null) temporizador.detener();
                vista.cerrarVentana();
            }
        });
    }
}

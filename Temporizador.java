package modelo;

import vista.PomodoroView;

public class Temporizador {
    private int tiempoTrabajo;
    private int tiempoDescanso;
    private boolean enPausa = false;
    private boolean detenido = false;

    private final PomodoroView vista;

    public Temporizador(int trabajo, int descanso, PomodoroView vista, Musica musica) {
        this.tiempoTrabajo = trabajo;
        this.tiempoDescanso = descanso;
        this.vista = vista;
    }

    public void iniciarTrabajo(Runnable alFinalizar) {
        iniciarTemporizador(tiempoTrabajo, "TRABAJO", alFinalizar);
    }

    public void iniciarDescanso(Runnable alFinalizar) {
        iniciarTemporizador(tiempoDescanso, "DESCANSO", alFinalizar);
    }

    private void iniciarTemporizador(int minutos, String tipo, Runnable alFinalizar) {
        detenido = false;
        enPausa = false;

        new Thread(() -> {
            int totalSegundos = minutos * 60;
            vista.setTipoTexto(tipo);
            vista.setEstadoTexto("Temporizador en curso");

            while (totalSegundos > 0 && !detenido) {
                if (!enPausa) {
                    int mins = totalSegundos / 60;
                    int secs = totalSegundos % 60;
                    vista.actualizarEstado(String.format("%02d:%02d", mins, secs));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }

                    totalSegundos--;
                } else {
                    vista.setEstadoTexto("Temporizador en pausa");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            vista.actualizarEstado("00:00");

            if (!detenido) {
                alFinalizar.run();
            }
        }).start();
    }

    public void pausar() {
        enPausa = !enPausa;
        if (!enPausa) {
            vista.setEstadoTexto("Temporizador en curso");
        }
    }

    public void detener() {
        detenido = true;
    }
}

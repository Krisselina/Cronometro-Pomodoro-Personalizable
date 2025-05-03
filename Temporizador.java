package pomodoro;

public class Temporizador {
    private int tiempoTrabajo;
    private int tiempoDescanso;

    public Temporizador(Usuario usuario) {
        this.tiempoTrabajo = usuario.getTiempoTrabajo();
        this.tiempoDescanso = usuario.getTiempoDescanso();
    }

    public void iniciarTrabajo() {
        iniciarTemporizador(tiempoTrabajo, "Tiempo de trabajo");
    }

    public void iniciarDescanso() {
        iniciarTemporizador(tiempoDescanso, "Tiempo de descanso");
    }

    private void iniciarTemporizador(int minutos, String mensaje) {
        int totalSegundos = minutos * 60;
        while (totalSegundos > 0) {
            int mins = totalSegundos / 60;
            int secs = totalSegundos % 60;
            System.out.printf("%s: %02d:%02d\r", mensaje, mins, secs);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Temporizador interrumpido");
            }
            totalSegundos--;
        }
        System.out.println("\n" + mensaje + " terminado.");
    }
}
class Interfaz {
    private Temporizador timer;
    private Musica musica;

    public Interfaz(Temporizador timer, Musica musica) {
        this.timer = timer;
        this.musica = musica;
    }

    public void mostrarTiempo() {
        System.out.println("Te quedan: " + ((PomodoroTimer) timer).getTiempoRestante() + " segundos");
    }

    public void actualizarEstado() {
        System.out.println(timer.estado);
    }

    public void controlarMusica(String accion) {
        if (accion.equalsIgnoreCase("reproducir")) {
            musica.reproducir();
        } else if (accion.equalsIgnoreCase("pausar")) {
            musica.pausar();
        } else if (accion.equalsIgnoreCase("detener")) {
            musica.detener();
        } else {
            System.out.println("Hola?");
        }
    }
}
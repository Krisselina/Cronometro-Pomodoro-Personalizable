class PomodoroTimer extends Temporizador {
    private int duracionTrabajo;
    private int duracionDescanso;
    private int ciclos;
    private int tiempoRestante;
    private int ciclosCompletados;
    private Notificacion notificacion;

    public PomodoroTimer(int duracionTrabajo, int duracionDescanso, int ciclos) {
        super(duracionTrabajo);
        this.duracionTrabajo = duracionTrabajo;
        this.duracionDescanso = duracionDescanso;
        this.ciclos = ciclos;
        this.tiempoRestante = duracionTrabajo * 60;
        this.ciclosCompletados = 0;
        this.notificacion = new Notificacion();
    }

    @Override
    public void iniciar() {
        this.estado = "Activo";
        notificacion.mostrarMensaje("Iniciado");
        ejecutarCiclo();
    }

    @Override
    public void pausar() {
        this.estado = "Pausado";
        notificacion.mostrarMensaje("Pausado");
    }

    @Override
    public void reiniciar() {
        this.estado = "Detenido";
        this.tiempoRestante = this.duracionTrabajo * 60;
        this.ciclosCompletados = 0;
        notificacion.mostrarMensaje("Reiniciado");
    }

    @Override
    public void actualizarTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        } else {
            notificacion.mostrarMensaje("Finalizado");
            cambiarEstado();
        }
    }

    public void cambiarEstado() {
        if (this.estado.equals("Activo")) {
            this.estado = "Descanso";
            this.tiempoRestante = this.duracionDescanso * 60;
            notificacion.mostrarMensaje("Inicia tu descanso");
        } else {
            this.ciclosCompletados++;
            if (this.ciclosCompletados >= this.ciclos) {
                this.estado = "Detenido";
                notificacion.mostrarMensaje("Finalizaste");
            } else {
                this.estado = "Activo";
                this.tiempoRestante = this.duracionTrabajo * 60;
                notificacion.mostrarMensaje("Retoma");
            }
        }
    }

    public void ejecutarCiclo() {
        while (ciclosCompletados < ciclos) {
            while (tiempoRestante > 0 && estado.equals("Activo")) {
                actualizarTiempo();
                System.out.println("Te quedan: " + tiempoRestante + " segundos");
            }
            cambiarEstado();
        }
    }
    
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void mostrarTiempoRestante() {
        int horas = tiempoRestante / 3600;
        int minutos = (tiempoRestante % 3600) / 60;
        int segundos = tiempoRestante % 60;
        System.out.println("Tiempo restante: " + horas + "h " + minutos + "m " + segundos + "s");
    }
}
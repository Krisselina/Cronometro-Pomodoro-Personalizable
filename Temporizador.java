abstract class Temporizador {
    protected int duracion;
    protected String estado;

    public Temporizador(int duracion) {
        this.duracion = duracion;
        this.estado = "Detenido";
    }

    public abstract void iniciar();
    public abstract void pausar();
    public abstract void reiniciar();
    public abstract void actualizarTiempo();
}
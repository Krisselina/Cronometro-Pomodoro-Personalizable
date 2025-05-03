package pomodoro;

public class TiempoInvalidoException extends Exception {
    public TiempoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
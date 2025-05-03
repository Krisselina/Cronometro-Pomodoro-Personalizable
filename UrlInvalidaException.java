package pomodoro;

public class UrlInvalidaException extends Exception {
    public UrlInvalidaException(String mensaje) {
        super(mensaje);
    }
}
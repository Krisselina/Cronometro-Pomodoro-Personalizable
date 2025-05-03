package pomodoro;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.configurar();

        Temporizador temporizador = new Temporizador(usuario);

        // NOTA: aquí debes ajustar el navegador que usas
        Musica musica = new Musica(); // o "google-chrome", o ruta absoluta
        Interfaz interfaz = new Interfaz(temporizador, musica, usuario);

        interfaz.iniciar();
    }
}
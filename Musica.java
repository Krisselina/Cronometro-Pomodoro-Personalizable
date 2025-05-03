package pomodoro;

import java.io.IOException;

public class Musica {
    private Process proceso;

    public void reproducir(String url) {
        try {
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

            ProcessBuilder builder = new ProcessBuilder(
                chromePath, "--new-window", "--app=" + url
            );
            proceso = builder.start();
        } catch (IOException e) {
            System.out.println("No se pudo abrir Chrome. Verifica la ruta del navegador o la URL.");
        }
    }

    public void detener() {
        if (proceso != null && proceso.isAlive()) {
            proceso.destroy();
        }
    }
}
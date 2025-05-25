package modelo;

import javax.sound.sampled.*;
import java.io.*;

public class Musica {
    private Process proceso;
    private Clip clipAlarma;
    private String ultimaUrlReproducida = "";

    public void reproducir(String url) {
        try {
            // Abre Chrome solo si la URL es diferente o el proceso fue cerrado
            if (!url.equals(ultimaUrlReproducida) || proceso == null || !proceso.isAlive()) {
                String chromePath = "C:/Program Files/Google/Chrome/Application/chrome.exe";
                ProcessBuilder builder = new ProcessBuilder(chromePath, "--new-window", "--app=" + url);
                proceso = builder.start();
                ultimaUrlReproducida = url;
            }
        } catch (IOException e) {
            System.out.println("No se pudo abrir Chrome: " + e.getMessage());
        }

    }
    public void detener() {
        // Detiene la playlist si está activa
        if (proceso != null && proceso.isAlive()) {
            proceso.destroy();
        }
        // También detiene la alarma si está sonando
        detenerAlarma();
        ultimaUrlReproducida = ""; // Reset para futuras reproducciones
    }

    public void reproducirAlarma() {
        detenerAlarma(); // Garantiza que no se superpongan clips
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("resources/alarma.wav");
            if (input == null) {
                System.out.println("No se pudo encontrar 'resources/alarma.wav' con getResourceAsStream");
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new BufferedInputStream(input));
            clipAlarma = AudioSystem.getClip();
            clipAlarma.open(audioInput);

            FloatControl control = (FloatControl) clipAlarma.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(control.getMaximum());

            clipAlarma.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir la alarma: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Este sistema no permite cambiar el volumen del clip.");
        }
    }

    public void detenerAlarma() {
        try {
            if (clipAlarma != null && clipAlarma.isRunning()) {
                clipAlarma.stop();
                clipAlarma.flush();
                clipAlarma.close();
                clipAlarma = null;
            }
        } catch (Exception e) {
            System.out.println("Error al detener la alarma: " + e.getMessage());
        }
    }
}

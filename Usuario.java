package pomodoro;

import java.io.*;
import java.util.*;

public class Usuario {
    private int tiempoTrabajo;
    private int tiempoDescanso;
    private String playlistTrabajo;
    private String playlistDescanso;
    private final String archivoConfig = "config.txt";

    public void configurar() {
        boolean configCargada = leerConfiguracionDesdeArchivo();

        if (configCargada) {
            System.out.println("✅ Configuración cargada desde archivo.\n");
            Scanner scanner = new Scanner(System.in);
            System.out.print("¿Quieres actualizar tu configuración? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) {
                pedirDatosManual();
                guardarConfiguracion();
            }
        } else {
            System.out.println("⚠️ No se encontró configuración válida. Se pedirá manualmente.");
            pedirDatosManual();
            guardarConfiguracion();
        }
    }

    private boolean leerConfiguracionDesdeArchivo() {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(archivoConfig)) {
            props.load(reader);

            tiempoTrabajo = Integer.parseInt(props.getProperty("trabajo"));
            tiempoDescanso = Integer.parseInt(props.getProperty("descanso"));
            playlistTrabajo = props.getProperty("playlistTrabajo");
            playlistDescanso = props.getProperty("playlistDescanso");

            return playlistTrabajo != null && playlistDescanso != null;

        } catch (FileNotFoundException e) {
            return false; // no hay archivo
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo configuración: " + e.getMessage());
            return false;
        }
    }

    private void guardarConfiguracion() {
        Properties props = new Properties();
        props.setProperty("trabajo", String.valueOf(tiempoTrabajo));
        props.setProperty("descanso", String.valueOf(tiempoDescanso));
        props.setProperty("playlistTrabajo", playlistTrabajo);
        props.setProperty("playlistDescanso", playlistDescanso);

        try (FileWriter writer = new FileWriter(archivoConfig)) {
            props.store(writer, "Configuración del usuario - Pomodoro");
            System.out.println("✅ Configuración guardada en config.txt\n");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar configuración: " + e.getMessage());
        }
    }

    private void pedirDatosManual() {
        Scanner scanner = new Scanner(System.in);
        boolean datosValidos = false;

        while (!datosValidos) {
            try {
                System.out.print("Minutos de trabajo (25 por defecto): ");
                String trabajoStr = scanner.nextLine();
                tiempoTrabajo = trabajoStr.isEmpty() ? 25 : parsearTiempo(trabajoStr);

                System.out.print("Minutos de descanso (5 por defecto): ");
                String descansoStr = scanner.nextLine();
                tiempoDescanso = descansoStr.isEmpty() ? 5 : parsearTiempo(descansoStr);

                System.out.print("URL de la playlist de trabajo: ");
                playlistTrabajo = scanner.nextLine();
                validarUrl(playlistTrabajo);

                System.out.print("URL de la playlist de descanso: ");
                playlistDescanso = scanner.nextLine();
                validarUrl(playlistDescanso);

                datosValidos = true;
            } catch (TiempoInvalidoException | UrlInvalidaException e) {
                System.out.println("Error: " + e.getMessage() + "\nPor favor, vuelve a intentarlo.\n");
            }
        }
    }

    private int parsearTiempo(String entrada) throws TiempoInvalidoException {
        try {
            int valor = Integer.parseInt(entrada);
            if (valor <= 0 || valor > 120) {
                throw new TiempoInvalidoException("El tiempo debe estar entre 1 y 120 minutos.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new TiempoInvalidoException("Ingresa un número entero válido.");
        }
    }

    private void validarUrl(String url) throws UrlInvalidaException {
        if (!url.startsWith("https://")) {
            throw new UrlInvalidaException("La URL debe comenzar con https://");
        }
    }

    public int getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public int getTiempoDescanso() {
        return tiempoDescanso;
    }

    public String getPlaylistTrabajo() {
        return playlistTrabajo;
    }

    public String getPlaylistDescanso() {
        return playlistDescanso;
    }
}
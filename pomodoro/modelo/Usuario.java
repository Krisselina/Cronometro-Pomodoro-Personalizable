package modelo;

import java.util.Scanner;
import java.io.*;
import java.util.Properties;

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
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("¿Quieres actualizar tu configuración? (s/n): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.equals("s")) {
                    pedirDatosManual(scanner);
                }
            } catch (Exception e) {
                System.out.println("Error al leer entrada del usuario: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ No se encontró configuración válida. Se pedirá manualmente.");
            try (Scanner scanner = new Scanner(System.in)) {
                pedirDatosManual(scanner);
            } catch (Exception e) {
                System.out.println("Error al leer entrada del usuario: " + e.getMessage());
            }
        }
    }

    private void pedirDatosManual(Scanner scanner) {
        System.out.print("Minutos de trabajo (25 por defecto): ");
        String trabajoStr = scanner.nextLine();
        tiempoTrabajo = trabajoStr.isEmpty() ? 25 : Integer.parseInt(trabajoStr);

        System.out.print("Minutos de descanso (5 por defecto): ");
        String descansoStr = scanner.nextLine();
        tiempoDescanso = descansoStr.isEmpty() ? 5 : Integer.parseInt(descansoStr);

        System.out.print("URL de la playlist de trabajo: ");
        playlistTrabajo = scanner.nextLine();

        System.out.print("URL de la playlist de descanso: ");
        playlistDescanso = scanner.nextLine();

        guardarConfiguracion();
    }

    private void guardarConfiguracion() {
        try (FileWriter writer = new FileWriter(archivoConfig)) {
            Properties props = new Properties();
            props.setProperty("trabajo", String.valueOf(tiempoTrabajo));
            props.setProperty("descanso", String.valueOf(tiempoDescanso));
            props.setProperty("playlistTrabajo", playlistTrabajo);
            props.setProperty("playlistDescanso", playlistDescanso);
            props.store(writer, null);
        } catch (IOException e) {
            System.out.println("Error al guardar configuración: " + e.getMessage());
        }
    }

    private boolean leerConfiguracionDesdeArchivo() {
        try (FileReader reader = new FileReader(archivoConfig)) {
            Properties props = new Properties();
            props.load(reader);

            tiempoTrabajo = Integer.parseInt(props.getProperty("trabajo", "25"));
            tiempoDescanso = Integer.parseInt(props.getProperty("descanso", "5"));
            playlistTrabajo = props.getProperty("playlistTrabajo", "");
            playlistDescanso = props.getProperty("playlistDescanso", "");

            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
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

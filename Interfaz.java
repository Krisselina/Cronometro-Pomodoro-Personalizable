package pomodoro;

import java.util.*;
import java.io.*;

public class Interfaz {
    private Temporizador temporizador;
    private Musica musica;
    private Usuario usuario;
    private List<String> frasesMotivadoras = new ArrayList<>();

    public Interfaz(Temporizador temporizador, Musica musica, Usuario usuario) {
        this.temporizador = temporizador;
        this.musica = musica;
        this.usuario = usuario;
        cargarFrasesDesdeArchivo();
    }

    private void cargarFrasesDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("frases.txt"), "UTF-8"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    frasesMotivadoras.add(linea.trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de frases no encontrado. Se usarán frases por defecto.");
            frasesMotivadoras = Arrays.asList(
                "Sigue adelante.",
                "Puedes con esto.",
                "Cada minuto cuenta."
            );
        } catch (IOException e) {
            System.out.println("Error al leer frases: " + e.getMessage());
        }
    }

    private void mostrarFraseMotivadora() {
        if (!frasesMotivadoras.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(frasesMotivadoras.size());
            System.out.println("\n" + frasesMotivadoras.get(index) + "\n");
        }
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            musica.reproducir(usuario.getPlaylistTrabajo());
            temporizador.iniciarTrabajo();
            musica.detener();

            System.out.print("Presiona Enter para iniciar el descanso...");
            scanner.nextLine();

            musica.reproducir(usuario.getPlaylistDescanso());
            temporizador.iniciarDescanso();
            musica.detener();

            mostrarFraseMotivadora();

            System.out.print("¿Deseas hacer otro ciclo? (s/n): ");
            continuar = scanner.nextLine().trim().equalsIgnoreCase("s");
        }

        System.out.println("¡Buen trabajo! Fin del Pomodoro.");
    }
}
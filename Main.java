import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la duración del período de concentración en minutos:");
        int duracionTrabajo = scanner.nextInt();
        
        System.out.println("Ingrese la duración del descanso en minutos:");
        int duracionDescanso = scanner.nextInt();
        
        System.out.println("Ingrese la cantidad de ciclos:");
        int ciclos = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Ingrese la URL de la playlist:");
        String playlistUrl = scanner.nextLine();

        PomodoroTimer timer = new PomodoroTimer(duracionTrabajo, duracionDescanso, ciclos);
        Musica musica = new Musica();
        musica.cargarPlaylist(playlistUrl);

        while (true) {
            System.out.println("Escribe un comando (iniciar, pausar, reiniciar, reproducir, detener, salir):");
            String comando = scanner.nextLine();

            if (comando.equalsIgnoreCase("iniciar")) {
                timer.iniciar();
            } else if (comando.equalsIgnoreCase("pausar")) {
                timer.pausar();
            } else if (comando.equalsIgnoreCase("reiniciar")) {
                timer.reiniciar();
            } else if (comando.equalsIgnoreCase("reproducir")) {
                musica.reproducir();
            } else if (comando.equalsIgnoreCase("detener")) {
                musica.detener();
            } else if (comando.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Comando no reconocido");
            }
        }

        scanner.close();
    }
}
class Usuario {
    private String nombre;
    private Temporizador configuracionTimer;
    private String playlistFavorita;

    public Usuario(String nombre, Temporizador configuracionTimer, String playlistFavorita) {
        this.nombre = nombre;
        this.configuracionTimer = configuracionTimer;
        this.playlistFavorita = playlistFavorita;
    }

    public void guardarPreferencias(Musica musica) {
        System.out.println("Preferencias guardadas para: " + nombre);
        musica.cargarPlaylist(playlistFavorita);
        configuracionTimer.iniciar();
    }

    public void cargarPreferencias() {
        System.out.println("Preferencias cargadas para: " + nombre);
    }
}
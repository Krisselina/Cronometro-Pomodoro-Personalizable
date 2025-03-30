class Musica {
    private String playlistActual;
    private String estadoReproduccion;

    public Musica() {
        this.playlistActual = "";
        this.estadoReproduccion = "Detenido";
    }

    public void cargarPlaylist(String link) {
        this.playlistActual = link;
        System.out.println("Playlist: " + link);
    }

    public void reproducir() {
        this.estadoReproduccion = "Reproduciendo";
        System.out.println("Reproduciendo");
    }

    public void pausar() {
        this.estadoReproduccion = "Pausado";
        System.out.println("Pausaste la Música");
    }

    public void detener() {
        this.estadoReproduccion = "Detenido";
        System.out.println("Paraste la Música");
    }
    
    public String getPlaylistActual() {
        return playlistActual;
    }
    
    public String getEstadoReproduccion() {
        return estadoReproduccion;
    }
}
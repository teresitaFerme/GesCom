package es.ucm.fdi.gescom.datacache;

public class Votacion {
    private String title, description;
    private String id;
    private int votosFavor, votosContra;

    public Votacion(String id, String title, String description, int votosFavor, int votosContra){
        this.id = id;
        this.title = title;
        this.description = description;
        this.votosFavor = votosFavor;
        this.votosContra = votosContra;
    }

    public int getVotosFavor() {
        return votosFavor;
    }

    public int getVotosContra() {
        return votosContra;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVotosContra(int votosContra) {
        this.votosContra = votosContra;
    }

    public void setVotosFavor(int votosFavor) {
        this.votosFavor = votosFavor;
    }
}

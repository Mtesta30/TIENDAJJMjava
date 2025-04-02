package co.edu.poli.TiendaJJM.modelo;

public class PoliticaEntrega {
    private String politica;

    public PoliticaEntrega(String politica) {
        this.politica = politica;
    }

    @Override
    public String toString() {
        return politica;
    }
}

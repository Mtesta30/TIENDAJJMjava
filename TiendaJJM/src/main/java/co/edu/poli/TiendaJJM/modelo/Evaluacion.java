package co.edu.poli.TiendaJJM.modelo;

public class Evaluacion {
    private String detalle;

    public Evaluacion(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return detalle;
    }
}

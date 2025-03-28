package co.edu.poli.TiendaJJM.modelo;

// Proveedor.java
public class Proveedor {
    private Evaluacion evaluacion;
    private Certificacion certificacion;
    private PoliticaEntrega politicaEntrega;

    // Constructor privado que recibe el builder
    private Proveedor(Builder builder) {
        this.evaluacion = builder.evaluacion;
        this.certificacion = builder.certificacion;
        this.politicaEntrega = builder.politicaEntrega;
    }

    // Inner class Builder
    public static class Builder {
        private Evaluacion evaluacion;
        private Certificacion certificacion;
        private PoliticaEntrega politicaEntrega;

        public Builder setEvaluacion(Evaluacion evaluacion) {
            this.evaluacion = evaluacion;
            return this;
        }

        public Builder setCertificacion(Certificacion certificacion) {
            this.certificacion = certificacion;
            return this;
        }

        public Builder setPoliticaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "evaluacion=" + evaluacion +
                ", certificacion=" + certificacion +
                ", politicaEntrega=" + politicaEntrega +
                '}';
    }
}
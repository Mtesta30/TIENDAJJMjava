package co.edu.poli.TiendaJJM.modelo;

public class ProductoAlimento extends Producto {
    private double aporteCalorico;

    public ProductoAlimento(int id, String descripcion, double aporteCalorico) {
        super(id, descripcion);
        this.aporteCalorico = aporteCalorico;
    }

    public double getAporteCalorico() {
        return aporteCalorico;
    }

    public void setAporteCalorico(double aporteCalorico) {
        this.aporteCalorico = aporteCalorico;
    }

    @Override
    public ProductoAlimento clone() {
        return new ProductoAlimento(this.getId(), this.getDescripcion(), this.aporteCalorico);
    }

    @Override
    public String toString() {
        return super.toString() + ", Aporte Calórico = " + aporteCalorico;
    }
}

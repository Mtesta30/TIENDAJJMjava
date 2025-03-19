package co.edu.poli.TiendaJJM.modelo;

public class ProductoElectrico extends Producto {
    private double voltaje;

    public ProductoElectrico(int id, String descripcion, double voltaje) {
        super(id, descripcion);
        this.voltaje = voltaje;
    }

    public double getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(double voltaje) {
        this.voltaje = voltaje;
    }

    @Override
    public ProductoElectrico clone() {
        return new ProductoElectrico(this.getId(), this.getDescripcion(), this.voltaje);
    }

    @Override
    public String toString() {
        return super.toString() + ", Voltaje = " + voltaje;
    }
}

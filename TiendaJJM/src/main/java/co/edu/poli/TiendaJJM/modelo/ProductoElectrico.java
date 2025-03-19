package co.edu.poli.TiendaJJM.modelo;

public class ProductoElectrico extends Producto {
    private int voltajeEntrada;
        public ProductoElectrico(int id, String descripcion, double precio) {
            super(id, descripcion, precio, null);
        }
    
    public int getVoltajeEntrada() {
        return voltajeEntrada;
    }

    public void setVoltajeEntrada(int voltajeEntrada) {
        this.voltajeEntrada = voltajeEntrada;
    }
}
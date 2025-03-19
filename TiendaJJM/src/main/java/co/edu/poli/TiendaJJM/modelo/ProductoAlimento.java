package co.edu.poli.TiendaJJM.modelo;

public class ProductoAlimento extends Producto {
    private int aporteCalorico;
    public ProductoAlimento(int id, String descripcion, double precio) {
        super(id, descripcion, precio, null);
    }

    public ProductoAlimento(int id, String descripcion, int aporteCalorico) {
        super(aporteCalorico, descripcion, id, descripcion);
        this.aporteCalorico = aporteCalorico;
    }

    public int getAporteCalorico() {
        return aporteCalorico;
    }

    public void setAporteCalorico(int aporteCalorico) {
        this.aporteCalorico = aporteCalorico;
    }
    
}

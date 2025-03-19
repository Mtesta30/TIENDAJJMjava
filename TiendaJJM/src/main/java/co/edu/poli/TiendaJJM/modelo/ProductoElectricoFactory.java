package co.edu.poli.TiendaJJM.modelo;

public class ProductoElectricoFactory implements ProductoFactory {
    @Override
    public Producto crearProducto(int id, String descripcion, double precio) {
        return new ProductoElectrico(id, descripcion, precio);
    }
}
package co.edu.poli.TiendaJJM.modelo;

public class ProductoAlimentoFactory implements ProductoFactory {
    @Override
    public Producto crearProducto(int id, String descripcion, double precio) {
        return new ProductoAlimento(id, descripcion, precio);
    }
}
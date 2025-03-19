package co.edu.poli.TiendaJJM.modelo;

public interface ProductoFactory {
    Producto crearProducto(int id, String descripcion, double precio);
}

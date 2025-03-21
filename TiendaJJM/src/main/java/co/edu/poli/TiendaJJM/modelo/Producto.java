package co.edu.poli.TiendaJJM.modelo;

public class Producto implements Prototype {
    private int id;
    private String descripcion;
    private double precio;
    private String tipo;

    // Constructor original
    public Producto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Nuevo constructor con precio y tipo
    public Producto(int id, String descripcion, double precio, String tipo) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Clonación del objeto incluyendo los nuevos atributos
    @Override
    public Producto clone() {
        return new Producto(this.id, this.descripcion, this.precio, this.tipo);
    }

    // Representación en cadena mejorada
    @Override
    public String toString() {
        return "Producto [ID = " + id + ", Descripción = " + descripcion + 
               ", Precio = $" + precio + ", Tipo = " + tipo + "]";
    }
}

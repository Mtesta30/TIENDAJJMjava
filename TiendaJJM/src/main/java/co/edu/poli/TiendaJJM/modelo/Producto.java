package co.edu.poli.TiendaJJM.modelo;

public class Producto implements CloneableProduct {
    private int id;  // Se agrega el ID
    private String nombre;
    private double precio;
    

    // Constructor
    public Producto(int id, String nombre, double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
       
    }

    // Implementación del método clone para clonar productos
    @Override
    public Producto clone() {
        return new Producto(this.id, this.nombre, this.precio, null );
    }

    // Nuevo método para obtener la descripción del producto
    public String getDescripcion() {
        return "Producto: " + nombre + ", Precio: $" + precio ;
    }

    // Getter para ID (corrige el error)
    public int getId() {
        return id;
    }

    // Getters y Setters
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    // Método toString para mostrar información del producto
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio
                ;}

	@Override
	public CloneableProduct clonar() {
		// TODO Auto-generated method stub
		return null;
	};
    }


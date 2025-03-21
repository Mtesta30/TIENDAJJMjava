package co.edu.poli.TiendaJJM.modelo;

// Hoja
public class Empleado implements EmpleadoComponent {
    private String nombre;
    private String puesto;

    public Empleado(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }

    @Override
    public String mostrarDetalles() {
        return("Empleado: " + nombre + ", Puesto: " + puesto);
    }
}
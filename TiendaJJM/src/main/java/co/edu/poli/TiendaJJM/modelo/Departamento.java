package co.edu.poli.TiendaJJM.modelo;

import java.util.ArrayList;
import java.util.List;

// Compuesto
public class Departamento implements EmpleadoComponent {
    private String nombre;
    private List<EmpleadoComponent> empleados = new ArrayList<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEmpleado(EmpleadoComponent empleado) {
        empleados.add(empleado);
    }

    public void eliminarEmpleado(EmpleadoComponent empleado) {
        empleados.remove(empleado);
    }

    @Override
    public String mostrarDetalles() {
        String S = null;
        
        for (EmpleadoComponent empleado : empleados) {
           S+=  empleado.mostrarDetalles();

        }
        return S;
    }
}
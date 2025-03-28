package co.edu.poli.TiendaJJM.modelo;

// Leaf
public class Employee implements Component {
    private String name;
    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public void showDetails() {
        // Este método ya no imprime directamente
    }
}
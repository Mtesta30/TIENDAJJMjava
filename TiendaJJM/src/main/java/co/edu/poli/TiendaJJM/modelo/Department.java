package co.edu.poli.TiendaJJM.modelo;

import java.util.ArrayList;
import java.util.List;

// Composite
public class Department implements Component {
    private String name;
    private List<Component> components = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public String getName() {
        return name;
    }

    @Override
    public void showDetails() {
        // Este método ya no imprime directamente
    }
}
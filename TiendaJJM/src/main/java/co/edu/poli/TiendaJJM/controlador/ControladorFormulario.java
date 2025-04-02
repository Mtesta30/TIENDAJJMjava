package co.edu.poli.TiendaJJM.controlador;

import co.edu.poli.TiendaJJM.modelo.*;
import co.edu.poli.TiendaJJM.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorFormulario {

    @FXML
    private Button Btt1, btnEliminar, btnActualizar, btnMostrar, btnAgregarProducto, btnEliminarProducto, btnClonar, btnNequi, btnPayPal, btnBuilder;

    @FXML
    private TextField txt1, txt2, txtEliminar, txtIdActualizar, txtNombreActualizar, txtIdProducto, txtNombreProducto;

    private DAOCRUD<Cliente> clienteDAO;
    private ProductoImplementacionDAO productoDAO;
    private Producto productoBase;

    @FXML
    private void crearProveedor() {
        Proveedor proveedor = new Proveedor.Builder()
                .setEvaluacion(new Evaluacion("Evaluación: Aprobada con 90%"))
                .setCertificacion(new Certificacion("Certificación: ISO 14001"))
                .setPoliticaEntrega(new PoliticaEntrega("Entrega en 48 horas"))
                .build();

        mostrarAlerta("✅ Proveedor Creado:\n" + proveedor.toString(), Alert.AlertType.INFORMATION);
    }

    @FXML
    void click(ActionEvent event) {
        Cliente c = new Cliente(txt2.getText(), txt1.getText());
        clienteDAO.agregar(c);
        mostrarAlerta("✅ Cliente guardado con éxito: \nID: " + c.getIdCliente() + "\nNombre: " + c.getNombre(), Alert.AlertType.INFORMATION);
    }

    @FXML
    void eliminarCliente(ActionEvent event) {
        String idCliente = txtEliminar.getText();
        if (clienteDAO.obtener(idCliente) != null) {
            clienteDAO.eliminar(idCliente);
            mostrarAlerta("✅ Cliente eliminado.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Cliente no encontrado.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void actualizarCliente(ActionEvent event) {
        Cliente cliente = clienteDAO.obtener(txtIdActualizar.getText());
        if (cliente != null) {
            cliente.setNombre(txtNombreActualizar.getText());
            clienteDAO.actualizar(cliente);
            mostrarAlerta("✅ Cliente actualizado.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Cliente no encontrado.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void agregarProducto(ActionEvent event) {
        Producto p = new Producto(0, txtIdProducto.getText(), 0, txtNombreProducto.getText());
        productoDAO.agregar(p);
        mostrarAlerta("✅ Producto agregado con éxito.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        String idProducto = txtIdProducto.getText();
        if (productoDAO.obtener(idProducto) != null) {
            productoDAO.eliminar(idProducto);
            mostrarAlerta("✅ Producto eliminado.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Producto no encontrado.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void clonarProducto(ActionEvent event) {
        if (productoBase == null) {
            productoBase = new Producto(0, "Producto Genérico", 0.0, "Categoría Genérica");
        }
        Producto productoClonado = productoBase.clone();
        mostrarAlerta("✅ Producto clonado: \n" + productoClonado, Alert.AlertType.INFORMATION);
    }

    @FXML
    void mostrarComponent(ActionEvent event) {
        Employee emp1 = new Employee("Juan", "Developer");
        Employee emp2 = new Employee("Maria", "Designer");
        Employee emp3 = new Employee("Carlos", "Manager");

        Department dept1 = new Department("IT");
        Department dept2 = new Department("Design");

        dept1.addComponent(emp1);
        dept1.addComponent(emp3);
        dept2.addComponent(emp2);

        Department company = new Department("Company");
        company.addComponent(dept1);
        company.addComponent(dept2);

        StringBuilder details = new StringBuilder();
        buildDetails(company, details);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jerarquía de Componentes");
        alert.setHeaderText("Estructura de la Empresa");
        alert.setContentText(details.toString());
        alert.showAndWait();
    }

    private void buildDetails(Component component, StringBuilder details) {
        if (component instanceof Employee) {
            details.append("  - ");
        }
        details.append(component.getClass().getSimpleName()).append(": ");
        if (component instanceof Employee) {
            Employee emp = (Employee) component;
            details.append(emp.getName()).append(", ").append(emp.getPosition());
        } else if (component instanceof Department) {
            Department dept = (Department) component;
            details.append(dept.getName());
        }
        details.append("\n");
        if (component instanceof Department) {
            for (Component child : ((Department) component).getComponents()) {
                buildDetails(child, details);
            }
        }
    }

    @FXML
    void pagarConNequi(ActionEvent event) {
        SistemaPago nequi = new AdaptadorNequi(new Nequi());
        boolean resultado = nequi.realizarPago(50000);

        if (resultado) {
            mostrarAlerta("✅ Pago realizado con Nequi exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Error en el pago con Nequi.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void pagarConPayPal(ActionEvent event) {
        SistemaPago paypal = new AdaptadorPayPal(new PayPal());
        boolean resultado = paypal.realizarPago(50000);

        if (resultado) {
            mostrarAlerta("✅ Pago realizado con PayPal exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Error en el pago con PayPal.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void mostrarCliente(ActionEvent event) {
        String idCliente = txtIdActualizar.getText();
        if (idCliente.isEmpty()) {
            mostrarAlerta("❌ Por favor, ingresa un ID.", Alert.AlertType.WARNING);
            return;
        }

        Cliente cliente = clienteDAO.obtener(idCliente);
        if (cliente != null) {
            mostrarAlerta("✅ Cliente encontrado: \nID: " + cliente.getIdCliente() + "\nNombre: " + cliente.getNombre(), Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Cliente no encontrado.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}

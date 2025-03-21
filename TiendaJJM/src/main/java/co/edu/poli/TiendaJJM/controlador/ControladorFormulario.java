package co.edu.poli.TiendaJJM.controlador;

import co.edu.poli.TiendaJJM.modelo.Certificacion;
import co.edu.poli.TiendaJJM.modelo.Cliente;
import co.edu.poli.TiendaJJM.modelo.Evaluacion;
import co.edu.poli.TiendaJJM.modelo.PoliticaEntrega;
import co.edu.poli.TiendaJJM.modelo.Producto;
import co.edu.poli.TiendaJJM.modelo.Proveedor;
import co.edu.poli.TiendaJJM.services.ClienteImplementacionDAO;
import co.edu.poli.TiendaJJM.services.ProductoImplementacionDAO;
import co.edu.poli.TiendaJJM.services.DAOCRUD;
import co.edu.poli.TiendaJJM.services.DatabaseConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorFormulario {

    @FXML
    private Button Btt1, btnEliminar, btnActualizar, btnMostrar, btnAgregarProducto, btnEliminarProducto, btnClonar;

    @FXML
    private TextField txt1, txt2, txtEliminar, txtIdActualizar, txtNombreActualizar, txtIdProducto, txtNombreProducto;

    private DAOCRUD<Cliente> clienteDAO;
    private ProductoImplementacionDAO productoDAO;
    
    private Producto productoBase;

     @FXML
    private Button btnBuilder;

    @FXML
    private void crearProveedor() {
        // Crear un proveedor usando el patrón Builder
        Proveedor proveedor = new Proveedor.Builder()
                .setEvaluacion(new Evaluacion("Evaluación: Aprobada con 90%"))
                .setCertificacion(new Certificacion("Certificación: ISO 14001"))
                .setPoliticaEntrega(new PoliticaEntrega("Entrega en 48 horas"))
                .build();

        // Mostrar el proveedor creado en una alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Proveedor Creado");
        alert.setHeaderText("Detalles del Proveedor");
        alert.setContentText(proveedor.toString());
        alert.showAndWait();
    }

    public ControladorFormulario() {
        try {
            clienteDAO = new ClienteImplementacionDAO();
            productoDAO = new ProductoImplementacionDAO();
            // Producto base para clonar
            productoBase = new Producto(1, "Laptop Dell", 109.0, "Electrónica");
        } catch (DatabaseConnectionException e) {
            System.out.println("❌ Error de conexión a la base de datos: " + e.getMessage());
        }
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
        Producto productoClonado = productoBase.clone();
        mostrarAlerta("✅ Producto clonado: \n" + productoClonado, Alert.AlertType.INFORMATION);
        System.out.println("Producto clonado: " + productoClonado);
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensaje);
        alerta.show();
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
}

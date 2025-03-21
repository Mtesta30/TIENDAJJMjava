package co.edu.poli.TiendaJJM.controlador;

import co.edu.poli.TiendaJJM.modelo.AdaptadorNequi;
import co.edu.poli.TiendaJJM.modelo.AdaptadorPayPal;
import co.edu.poli.TiendaJJM.modelo.Cliente;
import co.edu.poli.TiendaJJM.modelo.Nequi;
import co.edu.poli.TiendaJJM.modelo.PayPal;
import co.edu.poli.TiendaJJM.modelo.Producto;
import co.edu.poli.TiendaJJM.modelo.SistemaPago;
import co.edu.poli.TiendaJJM.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorFormulario {

    @FXML
    private Button Btt1, btnEliminar, btnActualizar, btnMostrar, btnAgregarProducto, btnEliminarProducto, btnClonar, btnNequi, btnPayPal;

    @FXML
    private TextField txt1, txt2, txtEliminar, txtIdActualizar, txtNombreActualizar, txtIdProducto, txtNombreProducto;

    private DAOCRUD<Cliente> clienteDAO;
    private ProductoImplementacionDAO productoDAO;

    private Producto productoBase;

    public ControladorFormulario() {
        try {
            clienteDAO = new ClienteImplementacionDAO();
            productoDAO = new ProductoImplementacionDAO();
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

    // MÉTODOS DE PAGO USANDO ADAPTER
    @FXML
    void pagarConNequi(ActionEvent event) {
        SistemaPago nequi = new AdaptadorNequi(new Nequi());
        boolean resultado = nequi.realizarPago(50000); // Simulación de pago

        if (resultado) {
            mostrarAlerta("✅ Pago realizado con Nequi exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Error en el pago con Nequi.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void pagarConPayPal(ActionEvent event) {
        SistemaPago paypal = new AdaptadorPayPal(new PayPal());
        boolean resultado = paypal.realizarPago(50000); // Simulación de pago

        if (resultado) {
            mostrarAlerta("✅ Pago realizado con PayPal exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("❌ Error en el pago con PayPal.", Alert.AlertType.ERROR);
        }
    }

    // MÉTODO PARA MOSTRAR ALERTAS
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}

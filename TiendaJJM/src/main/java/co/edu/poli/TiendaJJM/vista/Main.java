package co.edu.poli.TiendaJJM.vista;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.TiendaJJM.modelo.Cliente;
import co.edu.poli.TiendaJJM.modelo.Pedido;
import co.edu.poli.TiendaJJM.modelo.Producto;
import co.edu.poli.TiendaJJM.modelo.ProductoAlimentoFactory;
import co.edu.poli.TiendaJJM.modelo.ProductoElectricoFactory;
import co.edu.poli.TiendaJJM.services.ClienteImplementacionDAO;
import co.edu.poli.TiendaJJM.services.DAOCRUD;
import co.edu.poli.TiendaJJM.services.DatabaseConnectionException;
import co.edu.poli.TiendaJJM.services.ProductoImplementacionDAO;

public class Main {
    public static void main(String[] args) {
        // Crear fábricas de productos
        ProductoElectricoFactory factoryElectrico = new ProductoElectricoFactory();
        ProductoAlimentoFactory factoryAlimento = new ProductoAlimentoFactory();
        
        // Crear productos usando Factory 
        Producto producto1 = factoryElectrico.crearProducto(109, "Laptop Dell", 7);
        Producto producto2 = factoryAlimento.crearProducto(102, "Smartphone Samsung", 0);

        // Crear lista de productos
        List<Producto> productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);

        // Crear cliente con idCliente
        Cliente cliente = new Cliente("mario", "10265897456");

        // Crear pedido
        Pedido pedido = new Pedido(1, "2025-02-07", cliente, productos);

        // Mostrar detalles del pedido
        System.out.println("Pedido N°: " + pedido.getNumeroP());
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.println("Cliente: " + pedido.getCliente().getNombre());
        System.out.println("ID Cliente: " + pedido.getCliente().getIdCliente());
        System.out.println("Productos en el pedido:");

        // Mostrar todos los productos del pedido
        for (int i = 0; i < pedido.getProductos().size(); i++) {
            System.out.println("Producto " + (i + 1) + ": " + pedido.getProductos().get(i).getDescripcion());
        }

        try {
            // Uso de polimorfismo para crear objetos DAO
            DAOCRUD<Cliente> clienteDAO = new ClienteImplementacionDAO();
            DAOCRUD<Producto> productoDAO = new ProductoImplementacionDAO();

            // Agregar cliente y obtenerlo usando el ID correcto
            clienteDAO.agregar(cliente);
            Cliente clientePolimorfico = clienteDAO.obtener(cliente.getIdCliente());  // <-- ID correcto

            if (clientePolimorfico != null) {
                System.out.println("Cliente obtenido usando polimorfismo: " + clientePolimorfico.getNombre());
            } else {
                System.out.println("Error: No se encontró el cliente en la base de datos.");
            }

            // Agregar producto y obtenerlo usando polimorfismo
            productoDAO.agregar(producto1);
            Producto productoPolimorfico = productoDAO.obtener("109");

            if (productoPolimorfico != null) {
                System.out.println("Producto obtenido usando polimorfismo: " + productoPolimorfico.getDescripcion());
            } else {
                System.out.println("Error: No se encontró el producto en la base de datos.");
            }
            
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}

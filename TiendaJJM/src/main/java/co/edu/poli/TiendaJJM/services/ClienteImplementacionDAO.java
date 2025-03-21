package co.edu.poli.TiendaJJM.services;

import co.edu.poli.TiendaJJM.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteImplementacionDAO implements DAOCRUD<Cliente> {
    private Connection conexion;

    public ClienteImplementacionDAO() throws DatabaseConnectionException {
        this.conexion = GestionConexion.getConexion();
    }

    @Override
    public void agregar(Cliente cliente) {
        if (obtener(cliente.getIdCliente()) != null) {
            System.out.println("❌ El cliente con ID " + cliente.getIdCliente() + " ya existe.");
            return;
        }
        String sql = "INSERT INTO cliente (idCliente, nombre) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getIdCliente());
            pstmt.setString(2, cliente.getNombre());
            pstmt.executeUpdate();
            System.out.println("✅ Cliente guardado en la base de datos: " + cliente.getNombre());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al guardar el cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String idCliente) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, idCliente);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Cliente con ID " + idCliente + " eliminado.");
            } else {
                System.out.println("⚠️ No se encontró el cliente con ID " + idCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al eliminar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente obtener(String idCliente) {
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getString("nombre"), rs.getString("idCliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener el cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ? WHERE idCliente = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getIdCliente());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Cliente actualizado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el cliente con ID " + cliente.getIdCliente());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(rs.getString("nombre"), rs.getString("idCliente")));
            }
            System.out.println("✅ Lista de clientes obtenida con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener la lista de clientes: " + e.getMessage());
        }
        return clientes;
    }
}

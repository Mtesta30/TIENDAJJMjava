package co.edu.poli.TiendaJJM.services;

import co.edu.poli.TiendaJJM.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoImplementacionDAO implements DAOCRUD<Producto> {
    private Connection conexion;

    public ProductoImplementacionDAO() throws DatabaseConnectionException {
        this.conexion = GestionConexion.getConexion();
    }

    @Override
    public void agregar(Producto producto) {
        if (obtener(String.valueOf(producto.getId())) != null) {
            System.out.println("El producto con ID " + producto.getId() + " ya existe.");
            return;
        }
        String sql = "INSERT INTO producto (id, descripcion) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, producto.getId());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtener(String id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Producto(rs.getInt("id"), rs.getString("descripcion"), 0, sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto SET descripcion = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, producto.getDescripcion());
            pstmt.setInt(2, producto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("descripcion"), 0, sql));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public List<Producto> buscarPorDescripcion(String descripcion) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE descripcion LIKE ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + descripcion + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("descripcion"), 0, sql));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
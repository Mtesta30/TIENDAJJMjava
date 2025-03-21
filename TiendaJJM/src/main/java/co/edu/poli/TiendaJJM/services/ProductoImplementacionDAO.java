package co.edu.poli.TiendaJJM.services;

import co.edu.poli.TiendaJJM.modelo.Producto;
import co.edu.poli.TiendaJJM.modelo.ProductoElectrico;
import co.edu.poli.TiendaJJM.modelo.ProductoAlimento;

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

        String sql = "INSERT INTO producto (id, descripcion, tipo, voltaje, aporteCalorico) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, producto.getId());
            pstmt.setString(2, producto.getDescripcion());

            if (producto instanceof ProductoElectrico) {
                pstmt.setString(3, "electrico");
                pstmt.setDouble(4, ((ProductoElectrico) producto).getVoltaje());
                pstmt.setNull(5, java.sql.Types.DOUBLE);
            } else if (producto instanceof ProductoAlimento) {
                pstmt.setString(3, "alimento");
                pstmt.setNull(4, java.sql.Types.DOUBLE);
                pstmt.setDouble(5, ((ProductoAlimento) producto).getAporteCalorico());
            } else {
                pstmt.setString(3, "generico");
                pstmt.setNull(4, java.sql.Types.DOUBLE);
                pstmt.setNull(5, java.sql.Types.DOUBLE);
            }

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
                String tipo = rs.getString("tipo");

                if ("electrico".equals(tipo)) {
                    return new ProductoElectrico(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("voltaje"));
                } else if ("alimento".equals(tipo)) {
                    return new ProductoAlimento(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("aporteCalorico"));
                } else {
                    return new Producto(rs.getInt("id"), rs.getString("descripcion"));
                }
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
        try (PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");

                if ("electrico".equals(tipo)) {
                    productos.add(new ProductoElectrico(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("voltaje")));
                } else if ("alimento".equals(tipo)) {
                    productos.add(new ProductoAlimento(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("aporteCalorico")));
                } else {
                    productos.add(new Producto(rs.getInt("id"), rs.getString("descripcion")));
                }
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
                String tipo = rs.getString("tipo");

                if ("electrico".equals(tipo)) {
                    productos.add(new ProductoElectrico(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("voltaje")));
                } else if ("alimento".equals(tipo)) {
                    productos.add(new ProductoAlimento(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("aporteCalorico")));
                } else {
                    productos.add(new Producto(rs.getInt("id"), rs.getString("descripcion")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}

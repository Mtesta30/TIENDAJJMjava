package co.edu.poli.TiendaJJM.services;

import co.edu.poli.TiendaJJM.services.DatabaseConnectionException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class GestionConexion {
    private static Connection conexion;

    public static Connection getConexion() throws DatabaseConnectionException {
        if (conexion == null) {
            try {
                inicializarConexion();
            } catch (DatabaseConnectionException e) {
                throw new DatabaseConnectionException("No se pudo establecer la conexión con la base de datos.", e);
            }
        }
        return conexion;
    }

    private static void inicializarConexion() throws DatabaseConnectionException {
        Properties props = new Properties();

        try (InputStream input = GestionConexion.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IOException("No se encontró el archivo config.properties en resources/");
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            if (url == null || url.isEmpty()) {
                throw new SQLException("La URL de conexión a la base de datos no puede estar vacía.");
            }
            if (user == null || user.isEmpty()) {
                throw new SQLException("El usuario de la base de datos no puede estar vacío.");
            }
            if (password == null) {
                password = "";
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);

            inicializarBaseDeDatos();

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos.", e);
        }
    }

    private static void inicializarBaseDeDatos() throws SQLException {
        String[] sqlStatements = {
            "CREATE TABLE IF NOT EXISTS cliente (idCliente VARCHAR(255) PRIMARY KEY, nombre VARCHAR(255) NOT NULL);",
            "CREATE TABLE IF NOT EXISTS producto (id INT AUTO_INCREMENT PRIMARY KEY, descripcion VARCHAR(255) NOT NULL);",
            "CREATE TABLE IF NOT EXISTS pedido (numeroP INT AUTO_INCREMENT PRIMARY KEY, fecha DATE NOT NULL, idCliente VARCHAR(255) NOT NULL, FOREIGN KEY (idCliente) REFERENCES cliente(idCliente));",
            "CREATE TABLE IF NOT EXISTS pedido_producto (idPedido INT NOT NULL, idProducto INT NOT NULL, FOREIGN KEY (idPedido) REFERENCES pedido(numeroP), FOREIGN KEY (idProducto) REFERENCES producto(id));"
        };

        try (Statement stmt = conexion.createStatement()) {
            for (String sql : sqlStatements) {
                stmt.execute(sql);
            }
        }
    }
}
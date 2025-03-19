package co.edu.poli.TiendaJJM.vista;

import co.edu.poli.TiendaJJM.modelo.Producto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App con funcionalidad para clonar productos
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	
        // Cargar el archivo FXML
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/co/edu/poli/TiendaJJM/vista/formulario.fxml"));
        
        // Crear botón para clonar un producto
        Button cloneButton = new Button("Clonar Producto");
        cloneButton.setLayoutX(50);
        cloneButton.setLayoutY(300);
        
        // Producto base para clonar
        Producto productoBase = new Producto(1, "Laptop Dell", 109.0, "Electrónica");
        
        // Acción del botón
        cloneButton.setOnAction(event -> {
            Producto productoClonado = productoBase.clone();
            System.out.println("Producto clonado: " + productoClonado);
        }); 

        // Agregar el botón a la interfaz
        root.getChildren().add(cloneButton);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TiendaJJM");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

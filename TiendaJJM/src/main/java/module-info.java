module co.edu.poli.TiendaJJM {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens co.edu.poli.TiendaJJM.controlador to javafx.fxml;
    exports co.edu.poli.TiendaJJM.controlador;
    exports co.edu.poli.TiendaJJM.vista;
}

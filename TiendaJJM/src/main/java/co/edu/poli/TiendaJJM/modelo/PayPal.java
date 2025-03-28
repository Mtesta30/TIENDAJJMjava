package co.edu.poli.TiendaJJM.modelo;

public class PayPal {
    public boolean procesarPago(double monto) {
        System.out.println("💳 Procesando pago con PayPal por $" + monto);
        return true; // Simulación de pago exitoso
    }
}

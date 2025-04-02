package co.edu.poli.TiendaJJM.modelo;

public class AdaptadorPayPal implements SistemaPago {
    private PayPal paypal;

    public AdaptadorPayPal(PayPal paypal) {
        this.paypal = paypal;
    }

    @Override
    public boolean realizarPago(double monto) {
        return paypal.procesarPago(monto);
    }
}
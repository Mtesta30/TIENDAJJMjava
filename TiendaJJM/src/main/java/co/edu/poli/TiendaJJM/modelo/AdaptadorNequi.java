package co.edu.poli.TiendaJJM.modelo;

public class AdaptadorNequi implements SistemaPago {
	   private Nequi nequi;

	    public AdaptadorNequi(Nequi nequi) {
	        this.nequi = nequi;
	    }

	    @Override
	    public boolean realizarPago(double monto) {
	        return nequi.pagarConNequi(monto);
	    }
	}
package co.edu.poli.TiendaJJM.modelo;

import java.util.List;

public class Cliente {
    private String nombre;
    private String idCliente;
    private List<Pedido> pedidos;

    public Cliente(String nombre, String idCliente) {
        this.nombre = nombre;
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", idCliente=" + idCliente + ", pedidos=" + pedidos + "]";
	}
    
}
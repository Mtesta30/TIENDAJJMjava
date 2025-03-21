package co.edu.poli.TiendaJJM.modelo;

import java.util.List;

public class Pedido {
    private int numeroP;
    private String fecha;
    private Cliente cliente;
    private List<Producto> productos;

    public Pedido(int numeroP, String fecha, Cliente cliente, List<Producto> productos) {
        this.numeroP = numeroP;
        this.fecha = fecha;
        this.cliente = cliente;
        this.productos = productos;
    }

    public int getNumeroP() {
        return numeroP;
    }

    public void setNumeroP(int numeroP) {
        this.numeroP = numeroP;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
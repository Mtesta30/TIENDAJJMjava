package co.edu.poli.TiendaJJM.services;

import java.util.List;

public interface DAOCRUD<T> {
    void agregar(T t);
    void eliminar(String id);
    T obtener(String id);
    void actualizar(T t);
    List<T> obtenerTodos();
}
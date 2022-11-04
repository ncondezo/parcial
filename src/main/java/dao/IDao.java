package dao;

import model.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

     List<T> listar();
     void guardar(T t);
     void eliminar(long id);
     Optional<T> buscar(long id);
     void modificar(T t);

}

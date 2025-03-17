package org.example.devs.repository;
import java.sql.SQLException;
import java.util.List;
//tipo generico para poder implementar en diferentes clases y poder trabajar con diferentes tipos de datos
public interface IRepository <T>{
    List<T> findAll() throws SQLException;

    // este metodo devuelve un objeto de T segun el id
    T getByID(Integer id) throws SQLException;

    //Este metodo nos permite llegar a modificar o crear un nuevo objeto T
    void save(T t) throws SQLException;

    //Este metodo nos permite segun el id eliminar datos

    void delete(Integer id) throws SQLException;

}

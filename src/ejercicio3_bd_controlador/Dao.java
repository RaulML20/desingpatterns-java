package ejercicio3_bd_controlador;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

public interface Dao<T> {
 T get(long id);

 List<T> getAll(Connection conn);
 List<T> getByID(Connection conn, int id);
 List<T> getByIDP(Connection conn, String id);
 List<T> updateEmp(Connection conn);
 String bacth(Connection conn);
 //METODOS NECESARIOS
}

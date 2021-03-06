package ejercicio3_bd_controlador;


import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {

    final static String url = "jdbc:mysql:///dept";
    final static String user = "root";
    final static String password = "1234";
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    @Override
    public int getSize() {
        return bcp.getSize();
    }
//add getUser, getURL....

    @Override
    public void shutdown() throws SQLException {
        bcp.shutdown();
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
// TODO Auto-generated method stub
        return new EmpleadoDAO();
    }
    
    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new DepartamentoDAO();
    }
    
    @Override
    public ProyectoDAO getProyectosDAO() {
        return new ProyectoDAO();
    }
    
    @Override
    public FamiliarDAO getFamiliaresDAO() {
        return new FamiliarDAO();
    }
 /*
 * @Override public FamiliarDAO getFamiliarDAO() { // TODO Autogenerated method
 * stub return FamiliarDAO(); }
     */

   

   
}

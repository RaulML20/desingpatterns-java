package ejercicio3_bd_controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProyectoDAO implements Dao<Proyectos> {

    @Override
    public Proyectos get(long id) {
// TODO Auto-generated method stub
        return new Proyectos();
    }

    @Override
    public List<Proyectos> getAll(Connection conn) {
        List<Proyectos> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM PROY;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Proyectos>(totalRows);
            while (rs.next()) {
                String nameP = rs.getString(1);
                int presupuesto = rs.getInt(2);
                String descripcion = rs.getString(3);
                int deptno = rs.getInt(4);
                lista.add(new Proyectos(nameP, presupuesto, descripcion, deptno));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return lista;
    }
    /*RESTO DE METODOS NECESARIOS: INSERTAR, CONSULTAR…*/
//... SE ESCRIBIRAN TANTOS METODOS COMO SEAN NECESARIOS

    @Override
    public List<Proyectos> getByIDP(Connection conn, String id) {
        List<Proyectos> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM PROY WHERE NAMEP = '" + id + "'"+";");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Proyectos>(totalRows);
            while (rs.next()) {
                String nameP = rs.getString(1);
                int presupuesto = rs.getInt(2);
                String descripcion = rs.getString(3);
                int deptno = rs.getInt(4);
                lista.add(new Proyectos(nameP, presupuesto, descripcion, deptno));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return lista;
    }

    @Override
    public List<Proyectos> getByID(Connection conn, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proyectos> updateEmp(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String bacth(Connection conn) {
        try {
            ArrayList<Proyectos> proy = new ArrayList<Proyectos>();
            Proyectos p1 = new Proyectos("TEJER", 12000, "tejer seda", 10);
            Proyectos p2 = new Proyectos("CONSTRUIR", 13000, "construir viviendas", 20);
            Proyectos p3 = new Proyectos("MONTAR", 14000, "Montar aparatos", 10);
            proy.add(p1);
            proy.add(p2);
            proy.add(p3);
            
            PreparedStatement s = conn.prepareStatement("INSERT INTO PROY VALUES(?, ?, ?, ?)");
            
            for(int i = 0; i < proy.size(); i++){
                s.setString(1, proy.get(i).getNameP());
                s.setInt(2, proy.get(i).getPresupuesto());
                s.setString(3, proy.get(i).getDescripcion());   
                s.setInt(4, proy.get(i).getDeptno());
                s.addBatch();
            }
            
            s.executeBatch();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Proyectos añadidos";
    }
}
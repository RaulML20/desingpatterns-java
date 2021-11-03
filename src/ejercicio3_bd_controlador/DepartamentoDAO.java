package ejercicio3_bd_controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

public class DepartamentoDAO implements Dao<Departamentos> {

    @Override
    public Departamentos get(long id) {
// TODO Auto-generated method stub
        return new Departamentos();
    }

    @Override
    public List<Departamentos> getAll(Connection conn) {
        List<Departamentos> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM DEPT;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Departamentos>(totalRows);
            while (rs.next()) {
                int deptno = rs.getInt(1);
                String dname = rs.getString(2);
                String loc = rs.getString(3);
                lista.add(new Departamentos(deptno, dname, loc));
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
    public List<Departamentos> getByID(Connection conn, int id){
        List<Departamentos> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM DEPT WHERE DEPTNO = "+id+";");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Departamentos>(totalRows);
            while (rs.next()) {
                int deptno = rs.getInt(1);
                String dname = rs.getString(2);
                String loc = rs.getString(3);
                lista.add(new Departamentos(deptno, dname, loc));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return lista;
    }

    @Override
    public List<Departamentos> getByIDP(Connection conn, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Departamentos> updateEmp(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String bacth(Connection conn) {
        try {
            ArrayList<Departamentos> dept = new ArrayList<Departamentos>();
            Departamentos d1 = new Departamentos(50, "MANAGEMENT", "SANTIAGO");
            Departamentos d2 = new Departamentos(60, "DEPENDENCIES", "VIGO");
            Departamentos d3 = new Departamentos(70, "UTILITIES", "MADRID");
            dept.add(d1);
            dept.add(d2);
            dept.add(d3);
            
            PreparedStatement s = conn.prepareStatement("INSERT INTO DEPT VALUES(?, ?, ?)");
            
            for(int i = 0; i < dept.size(); i++){
                s.setInt(1, dept.get(i).getDeptno());
                s.setString(2, dept.get(i).getDname());
                s.setString(3, dept.get(i).getLoc());   
                s.addBatch();
            }
            
            s.executeBatch();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Departamentos añadidos";
    }
}

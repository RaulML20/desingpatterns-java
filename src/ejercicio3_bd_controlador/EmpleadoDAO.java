package ejercicio3_bd_controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmpleadoDAO implements Dao<Empleado> {

    @Override
    public Empleado get(long id) {
// TODO Auto-generated method stub
        return new Empleado();
    }

    @Override
    public List<Empleado> getAll(Connection conn) {
        List<Empleado> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM EMP;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Empleado>(totalRows);
            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                Date hiredate = rs.getDate(5);
                float sal = rs.getFloat(6);
                float comm = rs.getFloat(7);
                int deptno = rs.getInt(8);
                lista.add(new Empleado(empno, ename, deptno));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return lista;
    }
    
    @Override
    public List<Empleado> getByID(Connection conn, int id){
        List<Empleado> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM EMP WHERE EMPNO = "+id+";");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Empleado>(totalRows);
            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                Date hiredate = rs.getDate(5);
                float sal = rs.getFloat(6);
                float comm = rs.getFloat(7);
                int deptno = rs.getInt(8);
                lista.add(new Empleado(empno, ename, deptno));
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
    public List<Empleado> getByIDP(Connection conn, String ename) {
        List<Empleado> lista = null;
        try {
            
            PreparedStatement sInsert  = conn.prepareStatement("SELECT * FROM EMP WHERE ENAME = ?");
            sInsert.setString(1, ename); 
            ResultSet rs = sInsert.executeQuery();
            lista = new ArrayList<Empleado>();
            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename2 = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                Date hiredate = rs.getDate(5);
                float sal = rs.getFloat(6);
                float comm = rs.getFloat(7);
                int deptno = rs.getInt(8);
                lista.add(new Empleado(empno, ename2, deptno));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return lista;
    }

    @Override
    public List<Empleado> updateEmp(Connection conn) {
         List<Empleado> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery("SELECT * FROM EMP");
            lista = new ArrayList<Empleado>();
            
            while (rs.next()) {
                String nm = rs.getString("ENAME").toLowerCase();
                rs.updateString("ENAME", nm);
                rs.updateRow();
                String ename = rs.getString(2);
                lista.add(new Empleado(ename));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Empleado> updateMgr(Connection conn){
        List<Empleado> lista = null;
        Double mgr = 10.2;
        Double sum = 10.2;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery("SELECT 2*SUM(COMM), MGR FROM EMP WHERE COMM IS NOT NULL");
            PreparedStatement s2  = conn.prepareStatement("UPDATE EMP SET COMM=? WHERE EMPNO =?");
            lista = new ArrayList<Empleado>();

            while (rs.next()) {
                sum = rs.getDouble(1);
                mgr = rs.getDouble(2);
                s2.setDouble(1, sum);
                s2.setDouble(2, mgr);
                s2.execute();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Empleado> wievMgr(Connection conn){
        List<Empleado> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int rs = s.executeUpdate("CREATE or REPLACE view viewEMP(empno, ename, job, mgr, hiredate, sal, comm, deptno) as "
                    + "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM EMP WHERE EMPNO IN (SELECT MGR FROM EMP)");
            ResultSet rs2 = s.executeQuery("SELECT * from viewEMP");
            lista = new ArrayList<Empleado>();

            while(rs2.next()){
                int empno = rs2.getInt(1);
                String ename = rs2.getString(2);
                int deptno = rs2.getInt(8);
                lista.add(new Empleado(empno, ename, deptno));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Empleado> wievNMgr(Connection conn){
        List<Empleado> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int rs = s.executeUpdate("CREATE or REPLACE view viewNEMP(empno, ename, job, mgr, hiredate, sal, comm, deptno) as "
                    + "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM EMP WHERE JOB <> 'MANAGER'");
            ResultSet rs2 = s.executeQuery("SELECT * from viewNEMP");
            lista = new ArrayList<Empleado>();

            while(rs2.next()){
                int empno = rs2.getInt(1);
                String ename = rs2.getString(2);
                int deptno = rs2.getInt(8);
                lista.add(new Empleado(empno, ename, deptno));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Empleado> procedureMGR(Connection conn){
        List<Empleado> lista = null;
        try {
            CallableStatement cs = conn.prepareCall("{call GETMANAGER()}");
            ResultSet result = cs.executeQuery();
            lista = new ArrayList<Empleado>();

            while(result.next()){
                int empno = result.getInt(1);
                String ename = result.getString(2);
                int deptno = result.getInt(8);
                lista.add(new Empleado(empno, ename, deptno));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Empleado> procedureEMP(Connection conn, int empno){
        List<Empleado> lista = null;
        try {
            CallableStatement cs = conn.prepareCall("{call GETEMP2(?)}");
            lista = new ArrayList<Empleado>();
            
            cs.setInt(1, empno);
            ResultSet result = cs.executeQuery();
            
            result.next();
            String ename = result.getString("ENAME");
            lista.add(new Empleado(ename));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String bacth(Connection conn) {
         try {
            ArrayList<Empleado> emp = new ArrayList<Empleado>();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = null;
            Date d2 = null;
            Date d3 = null;
            try {
                d1 = formato.parse("1981-09-28");
                d2 = formato.parse("1981-09-28");
                d3 = formato.parse("1981-09-28");
            } 
            catch (ParseException ex) 
            {
                System.out.println(ex);
            }
            
            Empleado e1 = new Empleado(8245, "MARCOS", "SALESMAN", 7698, d1 , 1600, 200, 50);
            Empleado e2 = new Empleado(6245, "JUAN", "CLERK", 7839, d2 , 1800, 200, 50);
            Empleado e3 = new Empleado(9245, "FELIX", "SALESMAN", 7698, d3 , 2200, 200, 50);
            emp.add(e1);
            emp.add(e2);
            emp.add(e3);
            
            PreparedStatement s = conn.prepareStatement("INSERT INTO EMP VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            
            for(int i = 0; i < emp.size(); i++){
                String date = formato.format(emp.get(i).getHiredate());
                        
                s.setInt(1, emp.get(i).getEmpno());
                s.setString(2, emp.get(i).getEname());
                s.setString(3, emp.get(i).getJob());
                s.setInt(4, emp.get(i).getMgr());
                s.setString(5, date);
                s.setInt(6, emp.get(i).getSal());
                s.setInt(7, emp.get(i).getComm());
                s.setInt(8, emp.get(i).getDeptno());
                s.addBatch();
            }
            
            s.executeBatch();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Empleados añadidos";
    }
}

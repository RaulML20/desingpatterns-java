package ejercicio3_bd_controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FamiliarDAO implements Dao<Familiares> {

    @Override
    public Familiares get(long id) {
// TODO Auto-generated method stub
        return new Familiares();
    }

    @Override
    public List<Familiares> getAll(Connection conn) {
        List<Familiares> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM FAMILIAR;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Familiares>(totalRows);
            while (rs.next()) {
                int empno = rs.getInt(1);
                String nomF = rs.getString(2);
                int edad = rs.getInt(3);
                String parentesco = rs.getString(4);
                lista.add(new Familiares(empno, nomF, edad, parentesco));
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
    public List<Familiares> getByID(Connection conn, int id) {
        List<Familiares> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM FAMILIAR WHERE EMPNO = "+id+";");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Familiares>(totalRows);
            while (rs.next()) {
                int empno = rs.getInt(1);
                String nomF = rs.getString(2);
                int edad = rs.getInt(3);
                String parentesco = rs.getString(4);
                lista.add(new Familiares(empno, nomF, edad, parentesco));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista; 
    }

    @Override
    public List<Familiares> getByIDP(Connection conn, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Familiares> updateEmp(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String bacth(Connection conn) {
        try {
            ArrayList< Familiares> fam = new ArrayList<Familiares>();
            Familiares f1 = new  Familiares(7521, "LUIS", 23, "HIJO");
            Familiares f2 = new  Familiares(7499, "PEPE", 26, "HERMANO");
            Familiares f3 = new  Familiares(7499, "ROBERTO", 30, "PADRE");
            fam.add(f1);
            fam.add(f2);
            fam.add(f3);
            
            PreparedStatement s = conn.prepareStatement("INSERT INTO FAMILIAR VALUES(?, ?, ?, ?)");
            
            for(int i = 0; i < fam.size(); i++){
                s.setInt(1, fam.get(i).getEmpno());
                s.setString(2, fam.get(i).getNomF());
                s.setInt(3, fam.get(i).getEdad());
                s.setString(4, fam.get(i).getParentesco());   
                s.addBatch();
            }
            
            s.executeBatch();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Falimiares añadidos";
    }
}

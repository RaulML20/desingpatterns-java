package ejercicio3_bd_controlador;

import java.util.List;
import java.util.Scanner;

public class Controller {

    static List<Empleado> empleados;
    static List<Departamentos> departamentos;
    static List<Proyectos> proyectos;
    static List<Familiares> familiares;
    static List<Empleado> empleadosByID;
    static List<Departamentos> departamentosByID;
    static List<Proyectos> proyectosByID;
    static List<Familiares> familiaresByID;
    static List<Empleado> empleadosByIDP;
    static List<Empleado> updateEmp;
    static List<Empleado> updateMgr;
    static List<Empleado> viewMgr;
    static List<Empleado> viewNMgr;
    static List<Empleado> procedureMgr;
    static List<Empleado> procedureEmp;
    static String batchE;
    static String batchD;
    static String batchP;
    static String batchF;
    

        

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
//Create factory
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
//Create dAO
        EmpleadoDAO empDAO = mySQLFactory.getEmpleadoDAO();
        DepartamentoDAO deptDAO = mySQLFactory.getDepartamentoDAO();
        ProyectoDAO proyDAO = mySQLFactory.getProyectosDAO();
        FamiliarDAO famDAO = mySQLFactory.getFamiliaresDAO();
//CargarEmpleados
        try {
            empleados = empDAO.getAll(mySQLFactory.getConnection());
            departamentos = deptDAO.getAll(mySQLFactory.getConnection());
            proyectos = proyDAO.getAll(mySQLFactory.getConnection());
            familiares = famDAO.getAll(mySQLFactory.getConnection());
            viewMgr = empDAO.wievMgr(mySQLFactory.getConnection());
            viewNMgr = empDAO.wievNMgr(mySQLFactory.getConnection());
            procedureMgr = empDAO.procedureMGR(mySQLFactory.getConnection());
            
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
//Inicializar todo
//...
//Vista
        Vista v = new Vista();
        int opcion = v.mostrarMenu();
        switch (opcion) {
            case 1:
                String output = new String();
                for (int i = 0; i < empleados.size(); i++) {
                    output +=" empno = "+empleados.get(i).getEmpno()+",ename = "+empleados.get(i).getEname()+",deptno = "+empleados.get(i).getDeptno()+"\n";
                }
                v.showMessage(output);
                break;
            case 2:
                String output2 = new String();
                for (int i = 0; i < departamentos.size(); i++) {
                    output2 +=" Deptno = "+departamentos.get(i).getDeptno()+",Dname = "+departamentos.get(i).getDname()+",Localizacion = "+departamentos.get(i).getLoc()+"\n";
                }
                v.showMessage(output2);
                break;
            case 3:
                String output3 = new String();
                for (int i = 0; i < proyectos.size(); i++) {
                    output3 +="NameP = "+proyectos.get(i).getNameP()+",Presupuesto = "+proyectos.get(i).getPresupuesto()+",Descripcion = "+proyectos.get(i).getDescripcion()+",Deptno = "+proyectos.get(i).getDeptno()+"\n";
                }
                v.showMessage(output3);
                break;
            case 4:
                String output4 = new String();
                for (int i = 0; i < familiares.size(); i++) {
                        output4 +="Empno = "+familiares.get(i).getEmpno()+",NomF = "+familiares.get(i).getNomF()+",Edad = "+familiares.get(i).getEdad()+",Empno = "+familiares.get(i).getEmpno()+"\n";
                }
                v.showMessage(output4);
                break;
            case 5:
                System.out.println("Introduce el numero de empleado: ");
                int empno = teclado.nextInt();
                
                try {
                    empleadosByID = empDAO.getByID(mySQLFactory.getConnection(), empno);
                } catch (Exception e) {
                    e.printStackTrace();
                }
 
                String output5 = new String();
                for (int i = 0; i < empleadosByID.size(); i++) {
                    output5 +=" empno = "+empleadosByID.get(i).getEmpno()+",ename = "+empleadosByID.get(i).getEname()+",deptno = "+empleadosByID.get(i).getDeptno()+"\n";
                }
                v.showMessage(output5);
                break;
            case 6:
                System.out.println("Introduce el número de departamento: ");
                int deptno = teclado.nextInt();
                
                try {
                    departamentosByID = deptDAO.getByID(mySQLFactory.getConnection(), deptno);
                } catch (Exception e) {
                    e.printStackTrace();
                }
 
                String output6 = new String();
                for (int i = 0; i < departamentosByID.size(); i++) {
                    output6 +=" Deptno = "+departamentosByID.get(i).getDeptno()+",Dname = "+departamentosByID.get(i).getDname()+",Localizacion = "+departamentosByID.get(i).getLoc()+"\n";
                }
                v.showMessage(output6);
                break;
            case 7:
                System.out.println("Introduce el nombre de departamento: ");
                String namep = teclado.nextLine();
                
                try {
                    proyectosByID = proyDAO.getByIDP(mySQLFactory.getConnection(), namep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
   
                String output7 = new String();
                for (int i = 0; i < proyectosByID.size(); i++) {
                    output7 +=" NameP = "+proyectosByID.get(i).getNameP()+",Descripcion = "+proyectosByID.get(i).getDescripcion()+",presupuesto = "+proyectosByID.get(i).getPresupuesto()+",Deptno = "+proyectosByID.get(i).getDeptno()+"\n";
                }
                v.showMessage(output7);
                break;
            case 8:
                System.out.println("Introduce el numero del empleado con familiares: ");
                int empno2 = teclado.nextInt();
                
                try {
                    familiaresByID = famDAO.getByID(mySQLFactory.getConnection(), empno2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
   
                String output8 = new String();
                for (int i = 0; i < familiaresByID.size(); i++) {
                    output8 +=" Empno = "+familiaresByID.get(i).getEmpno()+",NomF = "+familiaresByID.get(i).getNomF()+",Edad = "+familiaresByID.get(i).getEdad()+"Parentesco = "+familiaresByID.get(i).getParentesco()+"\n";
                }
                v.showMessage(output8);
                break;
            case 9:
                System.out.println("Introduce el nombre del empleado: ");
                String ename = teclado.nextLine();
                
                try {
                    empleadosByIDP = empDAO.getByIDP(mySQLFactory.getConnection(), ename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
   
                String output9 = new String();
                for (int i = 0; i < empleadosByIDP.size(); i++) {
                    output9 +=" empno = "+empleadosByIDP.get(i).getEmpno()+",ename = "+empleadosByIDP.get(i).getEname()+",deptno = "+empleadosByIDP.get(i).getDeptno()+"\n";
                }
                v.showMessage(output9);
                break;
            case 10:
                try{
                    updateEmp = empDAO.updateEmp(mySQLFactory.getConnection());
                }catch(Exception e){e.printStackTrace();}
                
                String output10 = new String();
                for (int i = 0; i < updateEmp.size(); i++) {
                    output10 +=" "+updateEmp.get(i).getEname();
                }
                v.showMessage(output10);
                break;
            case 11:
                try{
                    updateMgr = empDAO.updateMgr(mySQLFactory.getConnection());
                }catch(Exception e){e.printStackTrace();}
                String output11 = new String();
                output11 = "COMM actualizado";
                v.showMessage(output11);
                break;
            case 12:
                String output12 = new String();
                for (int i = 0; i < viewMgr.size(); i++) {
                    output12 +=" empno = "+viewMgr.get(i).getEmpno()+",ename = "+viewMgr.get(i).getEname()+",deptno = "+viewMgr.get(i).getDeptno()+"\n";
                }
                v.showMessage(output12);
                break;
            case 13:
                String output13 = new String();
                for (int i = 0; i < viewNMgr.size(); i++) {
                    output13 +=" empno = "+viewNMgr.get(i).getEmpno()+",ename = "+viewNMgr.get(i).getEname()+",deptno = "+viewNMgr.get(i).getDeptno()+"\n";
                }
                v.showMessage(output13);
                break;
            case 14:
                String output14 = new String();
                for (int i = 0; i < procedureMgr.size(); i++) {
                    output14 +=" empno = "+procedureMgr.get(i).getEmpno()+",ename = "+procedureMgr.get(i).getEname()+",deptno = "+procedureMgr.get(i).getDeptno()+"\n";
                }
                v.showMessage(output14);
            case 15:
                System.out.println("Introduce la clave primaria del empleado: ");
                int empno3 = teclado.nextInt();
                
                try {
                    procedureEmp = empDAO.procedureEMP(mySQLFactory.getConnection(), empno3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
   
                String output15 = new String();
                for (int i = 0; i < procedureEmp.size(); i++) {
                    output15 +=" ename = "+procedureEmp.get(i).getEname();
                }
                v.showMessage(output15);
                break;
            case 16:
                try {
                    batchD = deptDAO.bacth(mySQLFactory.getConnection());
                    batchP = proyDAO.bacth(mySQLFactory.getConnection());
                    batchF = famDAO.bacth(mySQLFactory.getConnection());
                    batchE = empDAO.bacth(mySQLFactory.getConnection());
                    System.out.println(batchE);
                    System.out.println(batchD);
                    System.out.println(batchP);
                    System.out.println(batchF);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

package ejercicio3_bd_controlador;
public class Proyectos {
    private String nameP;
    private int presupuesto;
    private String descripcion;
    private int deptno;

    public Proyectos(String nameP, int presupuesto, String descripcion, int deptno) {
        this.nameP = nameP;
        this.presupuesto = presupuesto;
        this.descripcion = descripcion;
        this.deptno = deptno;
    }
    
    public Proyectos(){
    
    }
    
    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Integer presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
    
    
}

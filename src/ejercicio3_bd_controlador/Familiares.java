package ejercicio3_bd_controlador;
public class Familiares {
    private int empno;
    private String nomF;
    private int edad;
    private String parentesco;

    public Familiares(int empno, String nomF, int edad, String parentesco) {
        this.empno = empno;
        this.nomF = nomF;
        this.edad = edad;
        this.parentesco = parentesco;
    }
    
    public Familiares(){
        
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }


    public String getNomF() {
        return nomF;
    }

    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    
    
}

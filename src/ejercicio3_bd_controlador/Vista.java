package ejercicio3_bd_controlador;

import java.util.Scanner;

public class Vista {

    public int mostrarMenu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("selecciona una opcion:");
        System.out.println("1.) Mostrar empleados");
        System.out.println("2.) Mostrar Departamentos");
        System.out.println("3.) Mostrar Proyectos");
        System.out.println("4.) Mostrar Familiares");
        System.out.println("5.) Mostrar empleado por numero");
        System.out.println("6.) Mostrar departamento por numero");
        System.out.println("7.) Mostrar proyecto por nombre");
        System.out.println("8.) Mostrar familiares por el número de empleado del familiar");
        System.out.println("9.) Mostrar empleado por nombre con prepare statement");
        System.out.println("");
        System.out.println("");
        System.out.println("Ejercicio 15");
        System.out.println("10.) Actualizar nombres a minúsuclas");
        System.out.println("11.) Actualizar comm");
        System.out.println("Ejercicio 12");
        System.out.println("12.) Vista empleados jefes");
        System.out.println("13.) Vista empleados no jefes");
        System.out.println("Ejercicio 13");
        System.out.println("14.) Rutina almacenada jefes");
        System.out.println("15.) Rutina almacenada empleado");
        System.out.println("Ejercicio BACTH");
        System.out.println("16) Insertar por lotes");
        int opcion = teclado.nextInt();
        return opcion;
    }

    public boolean showMessage(String message) {
        System.out.println(message);
        return true;
    }
}

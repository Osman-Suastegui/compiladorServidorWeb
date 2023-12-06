package demoSpringBoot.demo.lexico.tablaDeSimbolos;

import java.util.ArrayList;
import java.util.List;

public class ManejadorErrores {
    private static ManejadorErrores instancia; // Instancia única de la tabla de símbolos

    private List<String> errores =  new ArrayList<>();;
    private ManejadorErrores() {}
    public static ManejadorErrores obtenerInstancia() {
        if (instancia == null) {
            instancia = new ManejadorErrores();
        }

        return instancia;
    }
    public void agregarError(String error) {
        errores.add(error);
    }
    public void mostrarErrores() {
        for(String error : errores) {
            System.out.println(error);
        }
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public int getCantidadErrores() {
        return errores.size();
    }

}

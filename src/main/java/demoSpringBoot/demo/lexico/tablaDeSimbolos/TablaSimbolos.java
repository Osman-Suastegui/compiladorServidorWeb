package demoSpringBoot.demo.lexico.tablaDeSimbolos;

import java.util.ArrayList;
import java.util.List;

public class TablaSimbolos {
    private static TablaSimbolos instancia; // Instancia única de la tabla de símbolos

//    unordered map
    private List<AtributosSimbolo> tabla = new ArrayList<>();


    private TablaSimbolos() {}
    public static TablaSimbolos obtenerInstancia() {
        if (instancia == null) {
            instancia = new TablaSimbolos();
        }
        return instancia;
    }


    public void insertar(AtributosSimbolo atributos) {
        tabla.add(atributos);


    }



    @Override
    public String toString() {
        String res = String.format("%-20s" ,"LEXEMA")  + String.format("%-20s","TIPO") + String.format("%-20s","VALOR") +  String.format("%-20s","RENGLON") + String.format("%-20s","COLUMNA") + "\n";

        for(AtributosSimbolo a : this.tabla) {
            res += String.format("%-20s" ,a.getLexema())  + String.format("%-20s",a.getTipo() ) + String.format("%-20s",a.getValor()) +  String.format("%-20s",a.getRenglon()) + String.format("%-20s",a.getColumna()) + "\n";
        }
        return res;

    }
}

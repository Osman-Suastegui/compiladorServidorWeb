package demoSpringBoot.demo.sintactico.Sentences.Sent;


import demoSpringBoot.demo.lexico.Nodo;

import java.util.List;

public class NodoPrograma extends NodoAST {

    private static NodoPrograma instancia = null;
    private List<NodoAST> sentencias;



    private NodoPrograma( List<NodoAST>  sentencias) {
        super("NodoPrograma", sentencias);
        this.sentencias = sentencias;
    }
    public static NodoPrograma obtenerInstancia( List<NodoAST>  sentencias) {
        if (instancia == null) {
            instancia = new NodoPrograma(sentencias);
        }
        return instancia;
    }
    public static NodoPrograma obtenerInstancia() {
        return instancia;
    }
    public static void limpiarInstancia() {
        instancia = null;
    }


    @Override
    public void print(int depth) {
        System.out.println("NodoPrograma");

        for (NodoAST sentencia : sentencias) {
            sentencia.print(depth + 1);
        }
    }


}

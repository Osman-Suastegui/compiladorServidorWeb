package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.ArrayList;

public class NodoDeclaracionConstante extends NodoAST {

    private String tipo;
    private String nombre;

    public NodoDeclaracionConstante(String tipo, String nombre) {
        super("NodoDeclaracionConstante", new ArrayList<>());
        this.tipo = tipo;
        this.nombre = nombre;
    }

    @Override
    public void print(int depth) {

            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println("-NodoDeclaracionConstante");

    }
}

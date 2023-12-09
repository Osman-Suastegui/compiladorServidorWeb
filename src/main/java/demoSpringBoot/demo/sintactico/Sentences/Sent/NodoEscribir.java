package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.ArrayList;

public class NodoEscribir extends NodoAST {

    private String contenidoEscribir;

    public NodoEscribir(String contenidoEscribir) {
        super("NodoEscribir",new ArrayList<>());
        this.contenidoEscribir = contenidoEscribir;
    }
    @Override
    public void print(int depth) {

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoEscribir");

    }
}
